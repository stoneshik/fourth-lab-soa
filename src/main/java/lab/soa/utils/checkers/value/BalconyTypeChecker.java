package lab.soa.utils.checkers.value;

import lab.soa.domain.models.BalconyType;

public class BalconyTypeChecker implements ValueChecker {
    public boolean isIncorrectValue(String rawStringValue) {
        return !BalconyType.isValidValue(rawStringValue);
    }
}
