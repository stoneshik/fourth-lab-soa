package lab.soa.presentation.dto.requests.coordinates;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinatesUpdateRequestDto {
    @JacksonXmlProperty(localName = "x")
    @NotNull
    @DecimalMin(value = "-993.0")
    private Float x;

    @JacksonXmlProperty(localName = "y")
    @NotNull
    private Long y;
}
