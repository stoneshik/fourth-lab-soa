package lab.soa.domain.specifications.flat;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.domain.specifications.fieldname.FlatFieldName;

public class FlatIntervalSpecification extends FlatIntervalAndRangeSpecification {
    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Integer fieldMinValue,
        Integer fieldMaxValue
    ) {
        if (fieldMinValue == null || fieldMaxValue == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldMinValue
                ),
                criteriaBuilder.lessThan(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldMaxValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Integer fieldMinValue,
        Integer fieldMaxValue
    ) {
        if (fieldMinValue == null || fieldMaxValue == null) {
            return null;
        }
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.greaterThan(
                    root.get(
                        flatNestedEntityFieldName.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldMinValue
                ),
                criteriaBuilder.lessThan(
                    root.get(
                        flatNestedEntityFieldName.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldMaxValue
                )
            );
    }

    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Long fieldMinValue,
        Long fieldMaxValue
    ) {
        if (fieldMinValue == null || fieldMaxValue == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldMinValue
                ),
                criteriaBuilder.lessThan(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldMaxValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Long fieldMinValue,
        Long fieldMaxValue
    ) {
        if (fieldMinValue == null || fieldMaxValue == null) {
            return null;
        }
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.greaterThan(
                    root.get(
                        flatNestedEntityFieldName.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldMinValue
                ),
                criteriaBuilder.lessThan(
                    root.get(
                        flatNestedEntityFieldName.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldMaxValue
                )
            );
    }

    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        Float fieldMinValue,
        Float fieldMaxValue
    ) {
        if (fieldMinValue == null || fieldMaxValue == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldMinValue
                ),
                criteriaBuilder.lessThan(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldMaxValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        Float fieldMinValue,
        Float fieldMaxValue
    ) {
        if (fieldMinValue == null || fieldMaxValue == null) {
            return null;
        }
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.greaterThan(
                    root.get(
                        flatNestedEntityFieldName.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldMinValue
                ),
                criteriaBuilder.lessThan(
                    root.get(
                        flatNestedEntityFieldName.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldMaxValue
                )
            );
    }

    public Specification<Flat> createSpecification(
        FlatFieldName fieldName,
        BigDecimal fieldMinValue,
        BigDecimal fieldMaxValue
    ) {
        if (fieldMinValue == null || fieldMaxValue == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.greaterThan(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldMinValue
                ),
                criteriaBuilder.lessThan(
                    root.get(
                        fieldName.getFieldName()
                    ),
                    fieldMaxValue
                )
            );
    }

    public Specification<Flat> createSpecificationFromEntity(
        FlatFieldName fieldName,
        BigDecimal fieldMinValue,
        BigDecimal fieldMaxValue
    ) {
        if (fieldMinValue == null || fieldMaxValue == null) {
            return null;
        }
        FlatFieldName flatNestedEntityFieldName = fieldName.getFlatNestedEntityFieldName();
        checkFlatNestedEntityFieldNameAndThrowExceptionIfNotCorrect(
            flatNestedEntityFieldName
        );
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.and(
                criteriaBuilder.greaterThan(
                    root.get(
                        flatNestedEntityFieldName.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldMinValue
                ),
                criteriaBuilder.lessThan(
                    root.get(
                        flatNestedEntityFieldName.getFieldName()
                    ).get(fieldName.getFieldName()),
                    fieldMaxValue
                )
            );
    }
}
