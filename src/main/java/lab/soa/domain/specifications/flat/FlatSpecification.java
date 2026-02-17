package lab.soa.domain.specifications.flat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.domain.specifications.fieldname.FlatFieldName;
import lab.soa.infrastructure.exceptions.IncorrectParamException;

public abstract class FlatSpecification {
    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        String fieldValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        String fieldValue
    );

    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Integer fieldValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Integer fieldValue
    );

    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Long fieldValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Long fieldValue
    );

    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Float fieldValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Float fieldValue
    );

    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        BigDecimal fieldValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        BigDecimal fieldValue
    );

    public abstract Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        LocalDateTime fieldValue
    );

    public abstract Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        LocalDateTime fieldValue
    );

    protected void checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
        FlatFieldName flatNestedEntityFieldName
    ) {
        if (flatNestedEntityFieldName == null) {
            throw new IncorrectParamException("Null nested entity field name");
        }
    }
}
