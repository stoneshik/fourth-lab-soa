package lab.soa.utils.parsers;

import java.math.BigDecimal;

import lab.soa.utils.checkers.value.PriceChecker;

public class PriceParser extends Parser<BigDecimal> {
    private static final PriceChecker VALUE_CHECKER = new PriceChecker();

    public BigDecimal parse(String value) {
        checkValueAndThrowExceptionIfIncorrect(VALUE_CHECKER, value);
        return new BigDecimal(value);
    }
}
