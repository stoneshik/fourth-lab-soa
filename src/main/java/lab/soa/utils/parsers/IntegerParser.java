package lab.soa.utils.parsers;

import lab.soa.utils.checkers.value.IntegerValueChecker;

public class IntegerParser extends Parser<Integer> {
    private static final IntegerValueChecker VALUE_CHECKER = new IntegerValueChecker();

    public Integer parse(String value) {
        checkValueAndThrowExceptionIfIncorrect(VALUE_CHECKER, value);
        return Integer.parseInt(value);
    }
}
