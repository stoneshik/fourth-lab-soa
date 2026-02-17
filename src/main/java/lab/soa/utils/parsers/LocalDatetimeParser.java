package lab.soa.utils.parsers;

import java.time.LocalDateTime;

import lab.soa.utils.checkers.value.LocalDatetimeValueChecker;

public class LocalDatetimeParser extends Parser<LocalDateTime> {
    private static final LocalDatetimeValueChecker VALUE_CHECKER = new LocalDatetimeValueChecker();

    public LocalDateTime parse(String value) {
        checkValueAndThrowExceptionIfIncorrect(VALUE_CHECKER, value);
        return LocalDateTime.parse(value);
    }
}
