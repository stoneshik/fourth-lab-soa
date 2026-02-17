package lab.soa.service.filters.flat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlatFilterParam {
    private FlatFilterField fieldFilter;
    private FlatFilterOperation operation;
    private String value;
    private String minValue;
    private String maxValue;
}
