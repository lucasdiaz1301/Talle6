package ui;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import model.ReportEntry;

/** Imprime el reporte tabulado con printf. */
public class ReportPrinter {
    private static final String DASH = "—";

    public void print(PrintWriter out, List<ReportEntry> entries, LocalDate today) {
        int nameWidth = "Nombre".length();
        for (ReportEntry e : entries) {
            nameWidth = Math.max(nameWidth, e.getPerson().getName().length());
        }
        String fmt = "%-" + nameWidth + "s  %-10s  %-13s  %-11s  %4s  %s%n";

        out.printf("Fecha del reporte: %s%n", today);
        out.printf(fmt, "Nombre", "Nacimiento", "Fallecimiento", "Signo", "Edad", "Días para cumpleaños");
        out.printf(fmt, "------", "----------", "-------------", "-----", "----", "--------------------");
        for (ReportEntry e : entries) {
            out.printf(fmt,
                    e.getPerson().getName(),
                    e.getPerson().getBirthDate(),
                    e.getPerson().isAlive() ? DASH : e.getPerson().getDeathDate().toString(),
                    e.getSign().getDisplayName(),
                    String.valueOf(e.getAge()),
                    e.getDaysToBirthday() == null ? DASH : String.valueOf(e.getDaysToBirthday()));
        }
        out.flush();
    }
}
