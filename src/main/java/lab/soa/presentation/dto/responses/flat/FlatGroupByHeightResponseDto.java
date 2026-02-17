package lab.soa.presentation.dto.responses.flat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlatGroupByHeightResponseDto {
    @JacksonXmlProperty(localName = "height")
    private Integer height;

    @JacksonXmlProperty(localName = "count")
    private Long count;
}
