package logic;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

/** Cálculo de edades y días para cumpleaños (el 29/feb pasa al 28/feb en años no bisiestos). */
public class AgeCalculator {

    /** Fecha del cumpleaños de {@code birth} en el año dado. */
    public LocalDate birthdayIn(LocalDate birth, int year) {
        if (birth.getMonthValue() == 2 && birth.getDayOfMonth() == 29 && !Year.isLeap(year)) {
            return LocalDate.of(year, 2, 28);
        }
        return LocalDate.of(year, birth.getMonthValue(), birth.getDayOfMonth());
    }

    /** Años completos entre el nacimiento y la fecha de referencia. */
    public int completedYears(LocalDate birth, LocalDate reference) {
        int years = reference.getYear() - birth.getYear();
        if (birthdayIn(birth, reference.getYear()).isAfter(reference)) {
            years--;
        }
        return years;
    }

    /** Días que faltan para el próximo cumpleaños (0 si es hoy). */
    public int daysToNextBirthday(LocalDate birth, LocalDate today) {
        LocalDate next = birthdayIn(birth, today.getYear());
        if (next.isBefore(today)) {
            next = birthdayIn(birth, today.getYear() + 1);
        }
        return (int) ChronoUnit.DAYS.between(today, next);
    }
}
