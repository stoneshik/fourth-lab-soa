package lab.soa.domain.specifications.fieldname;

public enum FlatFieldName implements FieldName {
    ID("id", TypeSpecification.ROOT_ENTITY, null),
    NAME("name", TypeSpecification.ROOT_ENTITY, null),

    COORDINATES("coordinates", null, null),
    COORDINATES_ID("id", TypeSpecification.NESTED_ENTITY, COORDINATES),
    COORDINATES_X("x", TypeSpecification.NESTED_ENTITY, COORDINATES),
    COORDINATES_Y("y", TypeSpecification.NESTED_ENTITY, COORDINATES),

    CREATION_DATE("creationDate", TypeSpecification.ROOT_ENTITY, null),
    AREA("area", TypeSpecification.ROOT_ENTITY, null),
    NUMBER_OF_ROOMS("numberOfRooms", TypeSpecification.ROOT_ENTITY, null),
    HEIGHT("height", TypeSpecification.ROOT_ENTITY, null),
    VIEW("view", TypeSpecification.ROOT_ENTITY, null),
    TRANSPORT("transport", TypeSpecification.ROOT_ENTITY, null),

    HOUSE("house", null, null),
    HOUSE_ID("id", TypeSpecification.NESTED_ENTITY, HOUSE),
    HOUSE_NAME("name", TypeSpecification.NESTED_ENTITY, HOUSE),
    HOUSE_YEAR("year", TypeSpecification.NESTED_ENTITY, HOUSE),
    HOUSE_NUMBER_OF_FLATS_ON_FLOOR(
        "numberOfFlatsOnFloor",
        TypeSpecification.NESTED_ENTITY,
        HOUSE
    ),

    PRICE("price", TypeSpecification.ROOT_ENTITY, null),
    BALCONY_TYPE("balconyType", TypeSpecification.ROOT_ENTITY, null),
    WALKING_MINUTES_TO_METRO("walkingMinutesToMetro", TypeSpecification.ROOT_ENTITY, null),
    TRANSPORT_MINUTES_TO_METRO("transportMinutesToMetro", TypeSpecification.ROOT_ENTITY, null),
    ;

    private final String fieldName;
    private final TypeSpecification typeSpecification;
    private final FlatFieldName flatNestedEntityFieldName;

    private FlatFieldName(
        String fieldName,
        TypeSpecification typeSpecification,
        FlatFieldName flatFieldName) {
        this.fieldName = fieldName;
        this.typeSpecification = typeSpecification;
        this.flatNestedEntityFieldName = flatFieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public TypeSpecification getTypeSpecification() {
        return typeSpecification;
    }

    public FlatFieldName getFlatNestedEntityFieldName() {
        return flatNestedEntityFieldName;
    }
}
