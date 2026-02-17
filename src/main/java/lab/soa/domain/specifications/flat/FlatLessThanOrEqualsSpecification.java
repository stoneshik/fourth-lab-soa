package lab.soa.domain.specifications.flat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.domain.specifications.fieldname.FlatFieldName;

public class FlatLessThanOrEqualsSpecification extends FlatSpecification {
    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        String fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        String fieldValue
    ) {
        if (fieldValue == null) return null;
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    flatNestedEntityFieldName.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }

    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Integer fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Integer fieldValue
    ) {
        if (fieldValue == null) return null;
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    flatNestedEntityFieldName.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }

    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Long fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Long fieldValue
    ) {
        if (fieldValue == null) return null;
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    flatNestedEntityFieldName.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }

    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Float fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Float fieldValue
    ) {
        if (fieldValue == null) return null;
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    flatNestedEntityFieldName.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }

    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        BigDecimal fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        BigDecimal fieldValue
    ) {
        if (fieldValue == null) return null;
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    flatNestedEntityFieldName.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }

    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        LocalDateTime fieldValue
    ) {
        if (fieldValue == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    fieldName.getFieldName()
                ),
                fieldValue
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        LocalDateTime fieldValue
    ) {
        if (fieldValue == null) return null;
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(
                root.get(
                    flatNestedEntityFieldName.getFieldName()
                ).get(fieldName.getFieldName()),
                fieldValue
            );
    }
}
