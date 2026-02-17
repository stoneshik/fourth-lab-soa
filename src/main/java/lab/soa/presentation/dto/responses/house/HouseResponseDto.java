package lab.soa.presentation.dto.responses.house;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseResponseDto {
    @JacksonXmlProperty(localName = "id")
    private Long id;

    @JacksonXmlProperty(localName = "name")
    @NotNull
    private String name;

    @JacksonXmlProperty(localName = "year")
    @Positive
    private Integer year;

    @JacksonXmlProperty(localName = "numberOfFlatsOnFloor")
    @Positive
    private Integer numberOfFlatsOnFloor;
}
