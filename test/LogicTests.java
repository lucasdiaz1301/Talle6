import java.time.LocalDate;
import java.util.List;
import logic.*;
import model.*;

/** Pruebas sencillas sin librerías externas. Ejecutar: java -cp out LogicTests */
public class LogicTests {
    static int fails = 0;

    static void check(String name, Object expected, Object actual) {
        boolean ok = expected.equals(actual);
        if (!ok) fails++;
        System.out.println((ok ? "OK   " : "FAIL ") + name + (ok ? "" : " esperado=" + expected + " actual=" + actual));
    }

    public static void main(String[] a) {
        ZodiacCalculator z = new ZodiacCalculator();
        AgeCalculator ag = new AgeCalculator();
        check("Aries inicio", ZodiacSign.ARIES, z.signOf(LocalDate.of(2000, 3, 21)));
        check("Piscis fin", ZodiacSign.PISCIS, z.signOf(LocalDate.of(2000, 3, 20)));
        check("Capricornio dic", ZodiacSign.CAPRICORNIO, z.signOf(LocalDate.of(1990, 12, 31)));
        check("Capricornio ene", ZodiacSign.CAPRICORNIO, z.signOf(LocalDate.of(1990, 1, 19)));
        check("Acuario inicio", ZodiacSign.ACUARIO, z.signOf(LocalDate.of(1990, 1, 20)));
        check("29 feb es Piscis", ZodiacSign.PISCIS, z.signOf(LocalDate.of(2000, 2, 29)));

        LocalDate b = LocalDate.of(2000, 2, 29);
        check("29feb edad 2027-02-27", 26, ag.completedYears(b, LocalDate.of(2027, 2, 27)));
        check("29feb edad 2027-02-28", 27, ag.completedYears(b, LocalDate.of(2027, 2, 28)));
        check("29feb dias desde 2027-02-20", 8, ag.daysToNextBirthday(b, LocalDate.of(2027, 2, 20)));
        check("29feb dias bisiesto 2028-02-20", 9, ag.daysToNextBirthday(b, LocalDate.of(2028, 2, 20)));
        check("cumple hoy", 0, ag.daysToNextBirthday(LocalDate.of(1977, 2, 2), LocalDate.of(2026, 2, 2)));
        check("Shakira dias (ejemplo)", 92, ag.daysToNextBirthday(LocalDate.of(1977, 2, 2), LocalDate.of(2026, 11, 2)));
        check("Shakira edad (ejemplo)", 49, ag.completedYears(LocalDate.of(1977, 2, 2), LocalDate.of(2026, 11, 2)));
        check("Curie años vividos", 66, ag.completedYears(LocalDate.of(1867, 11, 7), LocalDate.of(1934, 7, 4)));

        PersonReader r = new PersonReader();
        Person p = r.parseLine("Marie Curie, 7/11/1867, 4/7/1934");
        check("parse nombre", "Marie Curie", p.getName());
        check("parse fallecida", false, p.isAlive());

        List<ReportEntry> rep = new ReportService().build(java.util.Arrays.asList(
                r.parseLine("A, 1/1/1990"), r.parseLine("B, 1/1/1980"),
                r.parseLine("C, 1/1/1900, 1/1/1950"), r.parseLine("D, 1/1/1900, 1/1/1990")),
                LocalDate.of(2026, 11, 2));
        check("orden", "B,A,D,C", rep.get(0).getPerson().getName() + "," + rep.get(1).getPerson().getName()
                + "," + rep.get(2).getPerson().getName() + "," + rep.get(3).getPerson().getName());

        System.out.println(fails == 0 ? "TODAS LAS PRUEBAS PASARON" : fails + " PRUEBAS FALLARON");
        System.exit(fails == 0 ? 0 : 1);
    }
}
