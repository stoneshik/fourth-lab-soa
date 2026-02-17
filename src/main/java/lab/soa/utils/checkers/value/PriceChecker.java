package lab.soa.utils.checkers.value;

import java.util.regex.Pattern;

public class PriceChecker implements ValueChecker {
    private static final Pattern PRICE_PATTERN = Pattern.compile(
        "\\d{1,12}\\.\\d{2}"
    );

    public boolean isIncorrectValue(String rawStringValue) {
        return !PRICE_PATTERN.matcher(rawStringValue).matches();
    }
}
