package lab.soa.service.filters.flat;

import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.service.filters.FilterField;

public enum FlatFilterField implements FilterField {
    ID("id"),
    NAME("name"),
    COORDINATES_ID("coordinates.id"),
    COORDINATES_X("coordinates.x"),
    COORDINATES_Y("coordinates.y"),
    CREATION_DATE("creationDate"),
    AREA("area"),
    NUMBER_OF_ROOMS("numberOfRooms"),
    HEIGHT("height"),
    VIEW("view"),
    TRANSPORT("transport"),
    HOUSE_ID("house.id"),
    HOUSE_NAME("house.name"),
    HOUSE_YEAR("house.year"),
    HOUSE_NUMBER_OF_FLATS_ON_FLOOR("house.numberOfFlatsOnFloor"),
    PRICE("price"),
    BALCONY_TYPE("balconyType"),
    WALKING_MINUTES_TO_METRO("walkingMinutesToMetro"),
    TRANSPORT_MINUTES_TO_METRO("transportMinutesToMetro"),
    ;

    private final String filterFieldName;

    private FlatFilterField(String filterFieldName) {
        this.filterFieldName = filterFieldName;
    }

    public String getFilterFieldName() {
        return filterFieldName;
    }

    public static FlatFilterField parseFromString(String filterFieldRawString) {
        for (FlatFilterField flatFilterField: FlatFilterField.values()) {
            String flatFilterFieldString = flatFilterField.getFilterFieldName();
            if (filterFieldRawString.equals(flatFilterFieldString)) {
                return flatFilterField;
            }
        }
        throw new IncorrectParamException("Incorrect field value: " + filterFieldRawString);
    }
}
