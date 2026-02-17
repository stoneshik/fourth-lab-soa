package lab.soa.utils.parsers;

import lab.soa.utils.checkers.value.IntegerValueChecker;

public class LongParser extends Parser<Long> {
    private static final IntegerValueChecker VALUE_CHECKER = new IntegerValueChecker();

    public Long parse(String value) {
        checkValueAndThrowExceptionIfIncorrect(VALUE_CHECKER, value);
        return Long.parseLong(value);
    }
}
