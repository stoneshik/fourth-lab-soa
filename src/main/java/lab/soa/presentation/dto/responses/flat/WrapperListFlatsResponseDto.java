package lab.soa.presentation.dto.responses.flat;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "flatsPage")
public class WrapperListFlatsResponseDto {
    @JacksonXmlProperty(localName = "totalElements")
    private Long totalElements;

    @JacksonXmlProperty(localName = "totalPages")
    private Integer totalPages;

    @JacksonXmlProperty(localName = "currentPage")
    private Integer currentPage;

    @JacksonXmlProperty(localName = "pageSize")
    private Integer pageSize;

    @JacksonXmlElementWrapper(localName = "flats")
    @JacksonXmlProperty(localName = "flat")
    private List<FlatResponseDto> flats;
}
