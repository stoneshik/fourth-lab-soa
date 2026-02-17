package lab.soa.service.filters.flat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.service.filters.flat.factories.FlatFilterCheckerFactory;
import lab.soa.utils.checkers.value.ValueChecker;

@Component
public class StringToFlatFilterParamConverter {
    private static final Pattern FILTER_PATTERN =
        Pattern.compile("^([a-zA-Z.]+)\\((eq|ne|gt|lt|gte|lte|interval|range)\\)(.+)$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public FlatFilterParam convert(String source) {
        Matcher matcher = FILTER_PATTERN.matcher(source);
        if (!matcher.matches()) {
            throw new IncorrectParamException("Invalid filter format: " + source);
        }
        String filterFieldRawString = matcher.group(1);
        FlatFilterField filterField = FlatFilterField.parseFromString(filterFieldRawString);
        String operationRawString = matcher.group(2);
        FlatFilterOperation operation = FlatFilterOperation.parseFromString(operationRawString);
        String valueRawString = matcher.group(3);

        FlatFilterCheckerFactory flatFilterCheckerFactory = new FlatFilterCheckerFactory();
        ValueChecker filterValueChecker = flatFilterCheckerFactory.create(
            filterField
        );
        String filterMinValue = null;
        String filterMaxValue = null;
        if (operation.isRangeOrInterval()) {
            String[] rangeValues = valueRawString.split("_");
            if (rangeValues.length != 2) {
                throw new IncorrectParamException("Range and interval must have two values separated by '_'");
            }
            filterMinValue = rangeValues[0];
            filterMaxValue = rangeValues[1];
            if (isNotNumber(filterMinValue) || isNotNumber(filterMaxValue)) {
                throw new IncorrectParamException("Range and interval supports only numbers");
            }
            if (filterValueChecker.isIncorrectValue(filterMinValue) ||
                filterValueChecker.isIncorrectValue(filterMaxValue)) {
                throw new IncorrectParamException("Incorrect value: " + filterFieldRawString);
            }
        } else {
            if (filterValueChecker.isIncorrectValue(valueRawString)) {
                throw new IncorrectParamException("Incorrect value: " + filterFieldRawString);
            }
        }
        return FlatFilterParam.builder()
            .fieldFilter(filterField)
            .operation(operation)
            .value(valueRawString)
            .minValue(filterMinValue)
            .maxValue(filterMaxValue)
            .build();
    }

    public boolean isNotNumber(String numberString) {
        return !NUMBER_PATTERN.matcher(numberString).matches();
    }
}
