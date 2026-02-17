package lab.soa.utils.parsers;

import lab.soa.utils.checkers.value.ViewValueChecker;

public class ViewValueParserReturnString extends Parser<String> {
    private static final ViewValueChecker VALUE_CHECKER = new ViewValueChecker();

    public String parse(String value) {
        checkValueAndThrowExceptionIfIncorrect(VALUE_CHECKER, value);
        return value;
    }
}
