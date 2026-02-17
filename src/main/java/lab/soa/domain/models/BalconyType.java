package lab.soa.domain.models;

public enum BalconyType {
    WITH_BALCONY,
    WITHOUT_BALCONY,
    ;

    public static boolean isValidValue(String value) {
        if (value == null) {
            return false;
        }
        try {
            BalconyType.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
