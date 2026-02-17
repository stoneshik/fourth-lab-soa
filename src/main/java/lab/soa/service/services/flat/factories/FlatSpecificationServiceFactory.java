package lab.soa.service.services.flat.factories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import lab.soa.domain.models.Flat;
import lab.soa.domain.specifications.fieldname.FlatFieldName;
import lab.soa.domain.specifications.fieldname.TypeSpecification;
import lab.soa.domain.specifications.flat.FlatIntervalAndRangeSpecification;
import lab.soa.domain.specifications.flat.FlatSpecification;
import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.service.filters.flat.FlatFilterField;
import lab.soa.service.filters.flat.FlatFilterOperation;
import lab.soa.service.filters.flat.FlatFilterParam;
import lab.soa.utils.parsers.BalconyTypeParserReturnString;
import lab.soa.utils.parsers.FloatParser;
import lab.soa.utils.parsers.IntegerParser;
import lab.soa.utils.parsers.LocalDatetimeParser;
import lab.soa.utils.parsers.LongParser;
import lab.soa.utils.parsers.PriceParser;
import lab.soa.utils.parsers.StringParser;
import lab.soa.utils.parsers.TransportValueParserReturnString;
import lab.soa.utils.parsers.ViewValueParserReturnString;

public class FlatSpecificationServiceFactory {
    private final Map<FlatFilterField, FlatFieldName> mapFilterFieldToFlatFieldName = Map.ofEntries(
        Map.entry(FlatFilterField.ID, FlatFieldName.ID),
        Map.entry(FlatFilterField.NAME, FlatFieldName.NAME),
        Map.entry(FlatFilterField.COORDINATES_ID, FlatFieldName.COORDINATES_ID),
        Map.entry(FlatFilterField.COORDINATES_X, FlatFieldName.COORDINATES_X),
        Map.entry(FlatFilterField.COORDINATES_Y, FlatFieldName.COORDINATES_Y),
        Map.entry(FlatFilterField.CREATION_DATE, FlatFieldName.CREATION_DATE),
        Map.entry(FlatFilterField.AREA, FlatFieldName.AREA),
        Map.entry(FlatFilterField.NUMBER_OF_ROOMS, FlatFieldName.NUMBER_OF_ROOMS),
        Map.entry(FlatFilterField.HEIGHT, FlatFieldName.HEIGHT),
        Map.entry(FlatFilterField.VIEW, FlatFieldName.VIEW),
        Map.entry(FlatFilterField.TRANSPORT, FlatFieldName.TRANSPORT),
        Map.entry(FlatFilterField.HOUSE_ID, FlatFieldName.HOUSE_ID),
        Map.entry(FlatFilterField.HOUSE_NAME, FlatFieldName.HOUSE_NAME),
        Map.entry(FlatFilterField.HOUSE_YEAR, FlatFieldName.HOUSE_YEAR),
        Map.entry(FlatFilterField.HOUSE_NUMBER_OF_FLATS_ON_FLOOR, FlatFieldName.HOUSE_NUMBER_OF_FLATS_ON_FLOOR),
        Map.entry(FlatFilterField.PRICE, FlatFieldName.PRICE),
        Map.entry(FlatFilterField.BALCONY_TYPE, FlatFieldName.BALCONY_TYPE),
        Map.entry(FlatFilterField.WALKING_MINUTES_TO_METRO, FlatFieldName.WALKING_MINUTES_TO_METRO),
        Map.entry(FlatFilterField.TRANSPORT_MINUTES_TO_METRO, FlatFieldName.TRANSPORT_MINUTES_TO_METRO)
    );

    public Specification<Flat> create(
        FlatFilterParam flatFilterParam,
        FlatSpecification flatSpecification
    ) {
        checkFlatFilterParamAndThrowExceptionIfIncorrect(flatFilterParam);
        return createFlatSpecification(
            flatFilterParam,
            flatSpecification
        );
    }

    public Specification<Flat> create(
        FlatFilterParam flatFilterParam,
        FlatIntervalAndRangeSpecification flatIntervalAndRangeSpecification
    ) {
        checkFlatFilterParamAndThrowExceptionIfIncorrect(flatFilterParam);
        return createFlatIntervalAndRangeSpecification(
            flatFilterParam,
            flatIntervalAndRangeSpecification
        );
    }

    private void checkFlatFilterParamAndThrowExceptionIfIncorrect(FlatFilterParam flatFilterParam) {
        FlatFilterField flatFilterField = flatFilterParam.getFieldFilter();
        if (!mapFilterFieldToFlatFieldName.containsKey(flatFilterField)) {
            throw new IncorrectParamException("Incorrect field value: " + flatFilterField.getFilterFieldName());
        }
        FlatFilterOperation flatFilterOperation = flatFilterParam.getOperation();
        if (flatFilterOperation.isRangeOrInterval() &&
            (flatFilterParam.getMinValue() == null || flatFilterParam.getMaxValue() == null)) {
            throw new IncorrectParamException("Null min or max value in interval/range");
        }
    }

    private Specification<Flat> createFlatSpecification(
        FlatFilterParam flatFilterParam,
        FlatSpecification flatSpecification
    ) {
        FlatFilterField flatFilterField = flatFilterParam.getFieldFilter();
        String fieldValueString = flatFilterParam.getValue();
        FlatFieldName flatFieldName = mapFilterFieldToFlatFieldName.get(flatFilterField);
        TypeSpecification typeSpecification = flatFieldName.getTypeSpecification();
        if (typeSpecification == null) {
            throw new IncorrectParamException("Null type specification or nested entity field name");
        }
        switch (flatFilterField) {
            case ID,
                COORDINATES_ID,
                COORDINATES_Y,
                HOUSE_ID -> {
                LongParser parser = new LongParser();
                Long fieldValue = parser.parse(fieldValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatSpecification.createSpecification(flatFieldName, fieldValue);
                }
                return flatSpecification.createSpecificationFromEntity(flatFieldName, fieldValue);
            }
            case NAME,
                HOUSE_NAME -> {
                StringParser parser = new StringParser();
                String fieldValue = parser.parse(fieldValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatSpecification.createSpecification(flatFieldName, fieldValue);
                }
                return flatSpecification.createSpecificationFromEntity(flatFieldName, fieldValue);
            }
            case AREA,
                NUMBER_OF_ROOMS,
                HEIGHT,
                HOUSE_YEAR,
                HOUSE_NUMBER_OF_FLATS_ON_FLOOR,
                WALKING_MINUTES_TO_METRO,
                TRANSPORT_MINUTES_TO_METRO -> {
                IntegerParser parser = new IntegerParser();
                Integer fieldValue = parser.parse(fieldValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatSpecification.createSpecification(flatFieldName, fieldValue);
                }
                return flatSpecification.createSpecificationFromEntity(flatFieldName, fieldValue);
            }
            case COORDINATES_X -> {
                FloatParser parser = new FloatParser();
                Float fieldValue = parser.parse(fieldValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatSpecification.createSpecification(flatFieldName, fieldValue);
                }
                return flatSpecification.createSpecificationFromEntity(flatFieldName, fieldValue);
            }
            case CREATION_DATE -> {
                LocalDatetimeParser parser = new LocalDatetimeParser();
                LocalDateTime fieldValue = parser.parse(fieldValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatSpecification.createSpecification(flatFieldName, fieldValue);
                }
                return flatSpecification.createSpecificationFromEntity(flatFieldName, fieldValue);
            }
            case VIEW -> {
                ViewValueParserReturnString parser = new ViewValueParserReturnString();
                String fieldValue = parser.parse(fieldValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatSpecification.createSpecification(flatFieldName, fieldValue);
                }
                return flatSpecification.createSpecificationFromEntity(flatFieldName, fieldValue);
            }
            case TRANSPORT -> {
                TransportValueParserReturnString parser = new TransportValueParserReturnString();
                String fieldValue = parser.parse(fieldValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatSpecification.createSpecification(flatFieldName, fieldValue);
                }
                return flatSpecification.createSpecificationFromEntity(flatFieldName, fieldValue);
            }
            case PRICE -> {
                PriceParser parser = new PriceParser();
                BigDecimal fieldValue = parser.parse(fieldValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatSpecification.createSpecification(flatFieldName, fieldValue);
                }
                return flatSpecification.createSpecificationFromEntity(flatFieldName, fieldValue);
            }
            case BALCONY_TYPE -> {
                BalconyTypeParserReturnString parser = new BalconyTypeParserReturnString();
                String fieldValue = parser.parse(fieldValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatSpecification.createSpecification(flatFieldName, fieldValue);
                }
                return flatSpecification.createSpecificationFromEntity(flatFieldName, fieldValue);
            }
            default -> throw new IncorrectParamException(
                "Incorrect field value: " + flatFilterField.getFilterFieldName()
            );
        }
    }

    private Specification<Flat> createFlatIntervalAndRangeSpecification(
        FlatFilterParam flatFilterParam,
        FlatIntervalAndRangeSpecification flatIntervalAndRangeSpecification
    ) {
        FlatFilterField flatFilterField = flatFilterParam.getFieldFilter();
        String minValueString = flatFilterParam.getMinValue();
        String maxValueString = flatFilterParam.getMaxValue();
        FlatFieldName flatFieldName = mapFilterFieldToFlatFieldName.get(flatFilterField);
        TypeSpecification typeSpecification = flatFieldName.getTypeSpecification();
        if (typeSpecification == null) {
            throw new IncorrectParamException("Null type specification or nested entity field name");
        }
        switch (flatFilterField) {
            case ID,
                COORDINATES_ID,
                COORDINATES_Y,
                HOUSE_ID -> {
                LongParser parser = new LongParser();
                Long minFieldValue = parser.parse(minValueString);
                Long maxFieldValue = parser.parse(maxValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatIntervalAndRangeSpecification.createSpecification(
                        flatFieldName,
                        minFieldValue,
                        maxFieldValue
                    );
                }
                return flatIntervalAndRangeSpecification.createSpecificationFromEntity(
                    flatFieldName,
                    minFieldValue,
                    maxFieldValue
                );
            }
            case AREA,
                NUMBER_OF_ROOMS,
                HEIGHT,
                HOUSE_YEAR,
                HOUSE_NUMBER_OF_FLATS_ON_FLOOR,
                WALKING_MINUTES_TO_METRO,
                TRANSPORT_MINUTES_TO_METRO -> {
                IntegerParser parser = new IntegerParser();
                Integer minFieldValue = parser.parse(minValueString);
                Integer maxFieldValue = parser.parse(maxValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatIntervalAndRangeSpecification.createSpecification(
                        flatFieldName,
                        minFieldValue,
                        maxFieldValue
                    );
                }
                return flatIntervalAndRangeSpecification.createSpecificationFromEntity(
                    flatFieldName,
                    minFieldValue,
                    maxFieldValue
                );
            }
            case COORDINATES_X -> {
                FloatParser parser = new FloatParser();
                Float minFieldValue = parser.parse(minValueString);
                Float maxFieldValue = parser.parse(maxValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatIntervalAndRangeSpecification.createSpecification(
                        flatFieldName,
                        minFieldValue,
                        maxFieldValue
                    );
                }
                return flatIntervalAndRangeSpecification.createSpecificationFromEntity(
                    flatFieldName,
                    minFieldValue,
                    maxFieldValue
                );
            }
            case PRICE -> {
                PriceParser parser = new PriceParser();
                BigDecimal minFieldValue = parser.parse(minValueString);
                BigDecimal maxFieldValue = parser.parse(maxValueString);
                if (typeSpecification.equals(TypeSpecification.ROOT_ENTITY)) {
                    return flatIntervalAndRangeSpecification.createSpecification(
                        flatFieldName,
                        minFieldValue,
                        maxFieldValue
                    );
                }
                return flatIntervalAndRangeSpecification.createSpecificationFromEntity(
                    flatFieldName,
                    minFieldValue,
                    maxFieldValue
                );
            }
            default -> throw new IncorrectParamException(
                "Incorrect field value: " + flatFilterField.getFilterFieldName()
            );
        }
    }
}
