package lab.soa.domain.specifications.flat.factories;

import java.util.Map;

import lab.soa.domain.specifications.flat.FlatIntervalAndRangeSpecification;
import lab.soa.domain.specifications.flat.FlatIntervalSpecification;
import lab.soa.domain.specifications.flat.FlatRangeSpecification;
import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.service.filters.flat.FlatFilterOperation;

public class FlatIntervalAndRangeSpecificationFactory {
    private final Map<FlatFilterOperation, FlatIntervalAndRangeSpecification> mapFilterOperationToFilterIntervalAndRangeSpecification = Map.ofEntries(
        Map.entry(FlatFilterOperation.INTERVAL, new FlatIntervalSpecification()),
        Map.entry(FlatFilterOperation.RANGE, new FlatRangeSpecification())
    );

    public FlatIntervalAndRangeSpecification create(
        FlatFilterOperation filterOperation
    ) {
        if (!mapFilterOperationToFilterIntervalAndRangeSpecification.containsKey(filterOperation)) {
            throw new IncorrectParamException("Incorrect field operation name: " + filterOperation.getFilterOperationName());
        }
        return mapFilterOperationToFilterIntervalAndRangeSpecification.get(filterOperation);
    }
}
