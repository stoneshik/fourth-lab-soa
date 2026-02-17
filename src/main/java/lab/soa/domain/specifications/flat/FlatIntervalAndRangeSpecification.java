package lab.soa.domain.specifications.flat;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.domain.specifications.fieldname.FlatFieldName;
import lab.soa.infrastructure.exceptions.IncorrectParamException;

public abstract class FlatIntervalAndRangeSpecification {
    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Integer fieldMinValue,
        Integer fieldMaxValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Integer fieldMinValue,
        Integer fieldMaxValue
    );

    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Long fieldMinValue,
        Long fieldMaxValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Long fieldMinValue,
        Long fieldMaxValue
    );

    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Float fieldMinValue,
        Float fieldMaxValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Float fieldMinValue,
        Float fieldMaxValue
    );

    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        BigDecimal fieldMinValue,
        BigDecimal fieldMaxValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        BigDecimal fieldMinValue,
        BigDecimal fieldMaxValue
    );

    protected void checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
        FlatFieldName flatNestedEntityFieldName
    ) {
        if (flatNestedEntityFieldName == null) {
            throw new IncorrectParamException("Null nested entity field name");
        }
    }
}
