package logic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Person;

/** Lee el archivo de entrada con Scanner (sin librerías externas de CSV). */
public class PersonReader {

    public List<Person> read(String path) throws IOException {
        List<Person> people = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path), StandardCharsets.UTF_8.name())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    people.add(parseLine(line));
                }
            }
        }
        return people;
    }

    /** Formato: nombre, d/M/yyyy[, d/M/yyyy] */
    public Person parseLine(String line) {
        String[] parts = line.split(",");
        String name = parts[0].trim();
        LocalDate birth = parseDate(parts[1]);
        LocalDate death = parts.length > 2 ? parseDate(parts[2]) : null;
        return new Person(name, birth, death);
    }

    private LocalDate parseDate(String text) {
        String[] d = text.trim().split("/");
        return LocalDate.of(Integer.parseInt(d[2].trim()),
                            Integer.parseInt(d[1].trim()),
                            Integer.parseInt(d[0].trim()));
    }
}
