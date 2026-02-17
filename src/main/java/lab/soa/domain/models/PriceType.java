package lab.soa.domain.models;

public enum PriceType {
    CHEAPEST,
    EXPENSIVE,
    ;

    public static boolean isValidValue(String value) {
        if (value == null) {
            return false;
        }
        try {
            PriceType.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
