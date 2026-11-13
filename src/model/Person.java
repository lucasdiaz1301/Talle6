package model;

import java.time.LocalDate;

/** Una persona leída del archivo de entrada. */
public class Person {
    private final String name;
    private final LocalDate birthDate;
    private final LocalDate deathDate; // null si está viva

    public Person(String name, LocalDate birthDate, LocalDate deathDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public LocalDate getDeathDate() { return deathDate; }
    public boolean isAlive() { return deathDate == null; }
}
