package lab.soa.utils.checkers.value;

import java.util.regex.Pattern;

public class IntegerValueChecker implements ValueChecker {
    private static final Pattern INTEGER_PATTERN = Pattern.compile("-?\\d+");

    public boolean isIncorrectValue(String rawStringValue) {
        return !INTEGER_PATTERN.matcher(rawStringValue).matches();
    }
}
