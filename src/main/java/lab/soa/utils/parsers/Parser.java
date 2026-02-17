package lab.soa.utils.parsers;

import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.utils.checkers.value.ValueChecker;

public abstract class Parser<T> {
    public abstract T parse(String value);

    protected void checkValueAndThrowExceptionIfIncorrect(ValueChecker valueChecker, String value) {
        if (valueChecker.isIncorrectValue(value)) {
            throw new IncorrectParamException("Incorrect type value: " + value);
        }
    }
}
