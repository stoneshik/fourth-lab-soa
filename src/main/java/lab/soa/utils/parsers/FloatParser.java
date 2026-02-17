package lab.soa.utils.parsers;

import lab.soa.utils.checkers.value.FloatValueChecker;

public class FloatParser extends Parser<Float> {
    private static final FloatValueChecker VALUE_CHECKER = new FloatValueChecker();

    public Float parse(String value) {
        checkValueAndThrowExceptionIfIncorrect(VALUE_CHECKER, value);
        return Float.parseFloat(value);
    }
}
