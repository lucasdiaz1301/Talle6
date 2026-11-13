package logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Person;
import model.ReportEntry;

/** Calcula los campos nuevos y ordena: vivos (más viejo primero), luego fallecidos (más vivió primero). */
public class ReportService {
    private final ZodiacCalculator zodiac = new ZodiacCalculator();
    private final AgeCalculator ages = new AgeCalculator();

    public List<ReportEntry> build(List<Person> people, LocalDate today) {
        List<ReportEntry> alive = new ArrayList<>();
        List<ReportEntry> dead = new ArrayList<>();
        for (Person p : people) {
            if (p.isAlive()) {
                alive.add(new ReportEntry(p, zodiac.signOf(p.getBirthDate()),
                        ages.completedYears(p.getBirthDate(), today),
                        ages.daysToNextBirthday(p.getBirthDate(), today)));
            } else {
                dead.add(new ReportEntry(p, zodiac.signOf(p.getBirthDate()),
                        ages.completedYears(p.getBirthDate(), p.getDeathDate()), null));
            }
        }
        // List.sort es estable: los empates conservan el orden del archivo.
        alive.sort(Comparator.comparing((ReportEntry e) -> e.getPerson().getBirthDate()));
        dead.sort(Comparator.comparingInt(ReportEntry::getAge).reversed());

        List<ReportEntry> result = new ArrayList<>(alive);
        result.addAll(dead);
        return result;
    }
}
