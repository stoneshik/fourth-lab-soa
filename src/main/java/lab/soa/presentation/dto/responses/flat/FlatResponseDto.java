package lab.soa.presentation.dto.responses.flat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lab.soa.domain.models.BalconyType;
import lab.soa.domain.models.Transport;
import lab.soa.domain.models.View;
import lab.soa.presentation.dto.responses.coordinates.CoordinatesResponseDto;
import lab.soa.presentation.dto.responses.house.HouseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlatResponseDto {
    @JacksonXmlProperty(localName = "id")
    private Long id;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "coordinates")
    private CoordinatesResponseDto coordinates;

    @JacksonXmlProperty(localName = "creationDate")
    private LocalDateTime creationDate;

    @JacksonXmlProperty(localName = "area")
    private Integer area;

    @JacksonXmlProperty(localName = "numberOfRooms")
    private Integer numberOfRooms;

    @JacksonXmlProperty(localName = "height")
    private Integer height;

    @JacksonXmlProperty(localName = "view")
    private View view;

    @JacksonXmlProperty(localName = "transport")
    private Transport transport;

    @JacksonXmlProperty(localName = "house")
    private HouseResponseDto house;

    @JacksonXmlProperty(localName = "price")
    private BigDecimal price;

    @JacksonXmlProperty(localName = "balconyType")
    private BalconyType balconyType;

    @JacksonXmlProperty(localName = "walkingMinutesToMetro")
    private Integer walkingMinutesToMetro;

    @JacksonXmlProperty(localName = "transportMinutesToMetro")
    private Integer transportMinutesToMetro;
}
