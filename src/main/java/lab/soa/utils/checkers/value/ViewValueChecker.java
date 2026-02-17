package lab.soa.utils.checkers.value;

import lab.soa.domain.models.View;

public class ViewValueChecker implements ValueChecker {
    public boolean isIncorrectValue(String rawStringValue) {
        return !View.isValidValue(rawStringValue);
    }
}
