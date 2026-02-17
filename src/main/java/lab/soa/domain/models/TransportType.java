package lab.soa.domain.models;

public enum TransportType {
    TRANSPORT,
    WALKING,
    ;

    public static boolean isValidValue(String value) {
        if (value == null) {
            return false;
        }
        try {
            TransportType.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
