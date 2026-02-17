package lab.soa.utils.parsers;

import lab.soa.utils.checkers.value.TransportValueChecker;

public class TransportValueParserReturnString extends Parser<String> {
    private static final TransportValueChecker VALUE_CHECKER = new TransportValueChecker();

    public String parse(String value) {
        checkValueAndThrowExceptionIfIncorrect(VALUE_CHECKER, value);
        return value;
    }
}
