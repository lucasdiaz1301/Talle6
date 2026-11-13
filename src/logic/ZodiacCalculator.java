package logic;

import java.time.LocalDate;
import model.ZodiacSign;

/** Determina el signo zodiacal usando solo día y mes. */
public class ZodiacCalculator {

    public ZodiacSign signOf(LocalDate birthDate) {
        int month = birthDate.getMonthValue();
        int day = birthDate.getDayOfMonth();
        for (ZodiacSign sign : ZodiacSign.values()) {
            if (sign.contains(month, day)) {
                return sign;
            }
        }
        throw new IllegalStateException("Fecha sin signo: " + birthDate);
    }
}
