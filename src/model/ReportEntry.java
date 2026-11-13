package model;

/** Una persona con los tres campos calculados para el reporte. */
public class ReportEntry {
    private final Person person;
    private final ZodiacSign sign;
    private final int age;               // años completos (vividos si falleció)
    private final Integer daysToBirthday; // null si falleció

    public ReportEntry(Person person, ZodiacSign sign, int age, Integer daysToBirthday) {
        this.person = person;
        this.sign = sign;
        this.age = age;
        this.daysToBirthday = daysToBirthday;
    }

    public Person getPerson() { return person; }
    public ZodiacSign getSign() { return sign; }
    public int getAge() { return age; }
    public Integer getDaysToBirthday() { return daysToBirthday; }
}
