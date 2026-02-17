package lab.soa.utils.checkers.value;

import lab.soa.domain.models.Transport;

public class TransportValueChecker implements ValueChecker {
    public boolean isIncorrectValue(String rawStringValue) {
        return !Transport.isValidValue(rawStringValue);
    }
}
