package lab.soa.domain.models;

public enum SortType {
    ASC,
    DESC,
    ;

    public static boolean isValidValue(String value) {
        if (value == null) {
            return false;
        }
        try {
            SortType.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
