package model;

/** Signos zodiacales con sus intervalos (inclusivos) según el enunciado. */
public enum ZodiacSign {
    ARIES("Aries", 3, 21, 4, 19),
    TAURO("Tauro", 4, 20, 5, 20),
    GEMINIS("Géminis", 5, 21, 6, 20),
    CANCER("Cáncer", 6, 21, 7, 22),
    LEO("Leo", 7, 23, 8, 22),
    VIRGO("Virgo", 8, 23, 9, 22),
    LIBRA("Libra", 9, 23, 10, 22),
    ESCORPIO("Escorpio", 10, 23, 11, 21),
    SAGITARIO("Sagitario", 11, 22, 12, 21),
    CAPRICORNIO("Capricornio", 12, 22, 1, 19),
    ACUARIO("Acuario", 1, 20, 2, 18),
    PISCIS("Piscis", 2, 19, 3, 20);

    private final String displayName;
    private final int startKey; // mes*100 + día
    private final int endKey;

    ZodiacSign(String displayName, int startMonth, int startDay, int endMonth, int endDay) {
        this.displayName = displayName;
        this.startKey = startMonth * 100 + startDay;
        this.endKey = endMonth * 100 + endDay;
    }

    /** Indica si el día y mes dados caen dentro del signo (maneja el cambio de año). */
    public boolean contains(int month, int day) {
        int key = month * 100 + day;
        if (startKey <= endKey) {
            return key >= startKey && key <= endKey;
        }
        return key >= startKey || key <= endKey; // Capricornio
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
