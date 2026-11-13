package ui;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import logic.PersonReader;
import logic.ReportService;
import model.Person;
import model.ReportEntry;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: java -cp out ui.Main <entrada.txt> [salida.txt]");
            System.exit(1);
        }
        try {
            LocalDate today = LocalDate.now();
            List<Person> people = new PersonReader().read(args[0]);
            List<ReportEntry> report = new ReportService().build(people, today);

            ReportPrinter printer = new ReportPrinter();
            if (args.length >= 2) {
                try (PrintWriter writer = new PrintWriter(args[1], StandardCharsets.UTF_8.name())) {
                    printer.print(writer, report, today);
                }
            } else {
                PrintWriter console = new PrintWriter(System.out, true, StandardCharsets.UTF_8);
                printer.print(console, report, today);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
