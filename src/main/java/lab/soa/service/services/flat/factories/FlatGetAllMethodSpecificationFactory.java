package lab.soa.service.services.flat.factories;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.domain.specifications.flat.FlatIntervalAndRangeSpecification;
import lab.soa.domain.specifications.flat.FlatSpecification;
import lab.soa.domain.specifications.flat.factories.FlatIntervalAndRangeSpecificationFactory;
import lab.soa.domain.specifications.flat.factories.FlatSpecificationFactory;
import lab.soa.service.filters.flat.FlatFilterOperation;
import lab.soa.service.filters.flat.FlatFilterParam;

public class FlatGetAllMethodSpecificationFactory {
    private static final FlatSpecificationFactory FLAT_SPECIFICATION_FACTORY =
        new FlatSpecificationFactory();
    private static final FlatIntervalAndRangeSpecificationFactory FLAT_INTERVAL_AND_RANGE_SPECIFICATION_FACTORY =
            new FlatIntervalAndRangeSpecificationFactory();
    private static final FlatSpecificationServiceFactory FLAT_SPECIFICATION_SERVICE_FACTORY =
            new FlatSpecificationServiceFactory();

    public Specification<Flat> create(List<FlatFilterParam> filterParams) {
        if (filterParams == null) {
            return Specification.where(null);
        }
        Specification<Flat> globalSpecification = null;
        for (FlatFilterParam flatFilterParam: filterParams) {
            FlatFilterOperation filterOperation = flatFilterParam.getOperation();
            if (filterOperation.isRangeOrInterval()) {
                globalSpecification = createIntervalOrRangeSpecification(
                    globalSpecification,
                    flatFilterParam,
                    filterOperation
                );
                continue;
            }
            globalSpecification = createFlatSpecification(
                globalSpecification,
                flatFilterParam,
                filterOperation
            );
        }
        return globalSpecification;
    }

    private Specification<Flat> createIntervalOrRangeSpecification(
        Specification<Flat> globalSpecification,
        FlatFilterParam flatFilterParam,
        FlatFilterOperation filterOperation
    ) {
        FlatIntervalAndRangeSpecification flatIntervalAndRangeSpecification =
                    FLAT_INTERVAL_AND_RANGE_SPECIFICATION_FACTORY.create(filterOperation);
        Specification<Flat> localSpecification = FLAT_SPECIFICATION_SERVICE_FACTORY.create(
            flatFilterParam,
            flatIntervalAndRangeSpecification
        );
        if (globalSpecification == null) {
            return localSpecification;
        }
        return globalSpecification.and(
            localSpecification
        );
    }

    private Specification<Flat> createFlatSpecification(
        Specification<Flat> globalSpecification,
        FlatFilterParam flatFilterParam,
        FlatFilterOperation filterOperation
    ) {
        FlatSpecification flatSpecification = FLAT_SPECIFICATION_FACTORY.create(filterOperation);
        Specification<Flat> localSpecification = FLAT_SPECIFICATION_SERVICE_FACTORY.create(
            flatFilterParam,
            flatSpecification
        );
        if (globalSpecification == null) {
            return localSpecification;
        }
        return globalSpecification.and(
            localSpecification
        );
    }
}
