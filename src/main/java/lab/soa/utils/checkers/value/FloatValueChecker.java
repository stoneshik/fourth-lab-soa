package lab.soa.utils.checkers.value;

import java.util.regex.Pattern;

public class FloatValueChecker implements ValueChecker {
    private static final Pattern FLOAT_PATTERN = Pattern.compile("-?\\d+\\.\\d+");

    public boolean isIncorrectValue(String rawStringValue) {
        return !FLOAT_PATTERN.matcher(rawStringValue).matches();
    }
}
