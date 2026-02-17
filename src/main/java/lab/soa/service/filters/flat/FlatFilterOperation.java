package lab.soa.service.filters.flat;

import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.service.filters.FilterOperation;

public enum FlatFilterOperation implements FilterOperation {
    EQUALS("eq"),
    NOT_EQUALS("ne"),
    GREATER_THAN("gt"),
    LESS_THAN("lt"),
    GREATER_THAN_OR_EQUALS("gte"),
    LESS_THAN_OR_EQUALS("lte"),
    INTERVAL("interval"),
    RANGE("range"),
    ;

    private final String filterOperationName;

    private FlatFilterOperation(String filterOperationName) {
        this.filterOperationName = filterOperationName;
    }

    public String getFilterOperationName() {
        return filterOperationName;
    }

    public boolean isRangeOrInterval() {
        return this.equals(FlatFilterOperation.RANGE) ||
            this.equals(FlatFilterOperation.INTERVAL);
    }

    public static FlatFilterOperation parseFromString(String filterOperationRawString) {
        for (FlatFilterOperation filterOperation: FlatFilterOperation.values()) {
            String flatOperationString = filterOperation.getFilterOperationName();
            if (filterOperationRawString.equals(flatOperationString)) {
                return filterOperation;
            }
        }
        throw new IncorrectParamException("Incorrect filter operation value: " + filterOperationRawString);
    }
}
