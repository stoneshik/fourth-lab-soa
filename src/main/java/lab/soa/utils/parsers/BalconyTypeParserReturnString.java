package lab.soa.utils.parsers;

import lab.soa.utils.checkers.value.BalconyTypeChecker;

public class BalconyTypeParserReturnString extends Parser<String> {
    private static final BalconyTypeChecker VALUE_CHECKER = new BalconyTypeChecker();

    public String parse(String value) {
        checkValueAndThrowExceptionIfIncorrect(VALUE_CHECKER, value);
        return value;
    }
}
