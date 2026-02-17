package lab.soa.domain.specifications.flat.factories;

import java.util.Map;

import lab.soa.domain.specifications.flat.FlatEqualsSpecification;
import lab.soa.domain.specifications.flat.FlatGreaterThanOrEqualsSpecification;
import lab.soa.domain.specifications.flat.FlatGreaterThanSpecification;
import lab.soa.domain.specifications.flat.FlatLessThanOrEqualsSpecification;
import lab.soa.domain.specifications.flat.FlatLessThanSpecification;
import lab.soa.domain.specifications.flat.FlatNotEqualsSpecification;
import lab.soa.domain.specifications.flat.FlatSpecification;
import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.service.filters.flat.FlatFilterOperation;

public class FlatSpecificationFactory {
    private final Map<FlatFilterOperation, FlatSpecification> mapFilterOperationToFilterSpecification = Map.ofEntries(
        Map.entry(FlatFilterOperation.EQUALS, new FlatEqualsSpecification()),
        Map.entry(FlatFilterOperation.NOT_EQUALS, new FlatNotEqualsSpecification()),
        Map.entry(FlatFilterOperation.GREATER_THAN, new FlatGreaterThanSpecification()),
        Map.entry(FlatFilterOperation.LESS_THAN, new FlatLessThanSpecification()),
        Map.entry(FlatFilterOperation.GREATER_THAN_OR_EQUALS, new FlatGreaterThanOrEqualsSpecification()),
        Map.entry(FlatFilterOperation.LESS_THAN_OR_EQUALS, new FlatLessThanOrEqualsSpecification())
    );

    public FlatSpecification create(FlatFilterOperation filterOperation) {
        if (!mapFilterOperationToFilterSpecification.containsKey(filterOperation)) {
            throw new IncorrectParamException("Incorrect field operation name: " + filterOperation.getFilterOperationName());
        }
        return mapFilterOperationToFilterSpecification.get(filterOperation);
    }
}
