package lab.soa.service.filters.flat.factories;

import java.util.Map;

import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.service.filters.flat.FlatFilterField;
import lab.soa.utils.checkers.value.BalconyTypeChecker;
import lab.soa.utils.checkers.value.FloatValueChecker;
import lab.soa.utils.checkers.value.IntegerValueChecker;
import lab.soa.utils.checkers.value.LocalDatetimeValueChecker;
import lab.soa.utils.checkers.value.PriceChecker;
import lab.soa.utils.checkers.value.StringValueChecker;
import lab.soa.utils.checkers.value.TransportValueChecker;
import lab.soa.utils.checkers.value.ValueChecker;
import lab.soa.utils.checkers.value.ViewValueChecker;

public class FlatFilterCheckerFactory {
    private final Map<FlatFilterField, ValueChecker> mapFilterFieldToFilterValueChecker = Map.ofEntries(
        Map.entry(FlatFilterField.ID, new IntegerValueChecker()),
        Map.entry(FlatFilterField.NAME, new StringValueChecker()),
        Map.entry(FlatFilterField.COORDINATES_ID, new IntegerValueChecker()),
        Map.entry(FlatFilterField.COORDINATES_X, new FloatValueChecker()),
        Map.entry(FlatFilterField.COORDINATES_Y, new IntegerValueChecker()),
        Map.entry(FlatFilterField.CREATION_DATE, new LocalDatetimeValueChecker()),
        Map.entry(FlatFilterField.AREA, new IntegerValueChecker()),
        Map.entry(FlatFilterField.NUMBER_OF_ROOMS, new IntegerValueChecker()),
        Map.entry(FlatFilterField.HEIGHT, new IntegerValueChecker()),
        Map.entry(FlatFilterField.VIEW, new ViewValueChecker()),
        Map.entry(FlatFilterField.TRANSPORT, new TransportValueChecker()),
        Map.entry(FlatFilterField.HOUSE_ID, new IntegerValueChecker()),
        Map.entry(FlatFilterField.HOUSE_NAME, new StringValueChecker()),
        Map.entry(FlatFilterField.HOUSE_YEAR, new IntegerValueChecker()),
        Map.entry(FlatFilterField.HOUSE_NUMBER_OF_FLATS_ON_FLOOR, new IntegerValueChecker()),
        Map.entry(FlatFilterField.PRICE, new PriceChecker()),
        Map.entry(FlatFilterField.BALCONY_TYPE, new BalconyTypeChecker()),
        Map.entry(FlatFilterField.WALKING_MINUTES_TO_METRO, new IntegerValueChecker()),
        Map.entry(FlatFilterField.TRANSPORT_MINUTES_TO_METRO, new IntegerValueChecker())
    );

    public ValueChecker create(FlatFilterField filterField) {
        if (!mapFilterFieldToFilterValueChecker.containsKey(filterField)) {
            throw new IncorrectParamException("Incorrect field value: " + filterField.getFilterFieldName());
        }
        return mapFilterFieldToFilterValueChecker.get(filterField);
    }
}
