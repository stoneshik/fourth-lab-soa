package lab.soa.utils.checkers.value;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class LocalDatetimeValueChecker implements ValueChecker {
    public boolean isIncorrectValue(String rawStringValue) {
        try {
            LocalDateTime.parse(rawStringValue);
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }
}
