package lab.soa.domain.models;

public enum Transport {
    FEW,
    NONE,
    LITTLE,
    NORMAL,
    ENOUGH,
    ;

    public static boolean isValidValue(String value) {
        if (value == null) {
            return false;
        }
        try {
            Transport.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
