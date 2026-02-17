package lab.soa.presentation.dto.requests.flat;

import java.math.BigDecimal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lab.soa.domain.models.BalconyType;
import lab.soa.domain.models.Transport;
import lab.soa.domain.models.View;
import lab.soa.presentation.dto.requests.coordinates.CoordinatesCreateRequestDto;
import lab.soa.presentation.dto.requests.house.HouseCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "flat")
public class FlatRequestCreateDto {
    @JacksonXmlProperty(localName = "name")
    @NotNull
    @NotBlank
    private String name;

    @JacksonXmlProperty(localName = "coordinates")
    @Valid
    @NotNull
    private CoordinatesCreateRequestDto coordinates;

    @JacksonXmlProperty(localName = "area")
    @Positive
    private Integer area;

    @JacksonXmlProperty(localName = "numberOfRooms")
    @NotNull
    @Positive
    private Integer numberOfRooms;

    @JacksonXmlProperty(localName = "height")
    @NotNull
    @Positive
    private Integer height;

    @JacksonXmlProperty(localName = "view")
    @Enumerated(EnumType.STRING)
    private View view;

    @JacksonXmlProperty(localName = "transport")
    @Enumerated(EnumType.STRING)
    private Transport transport;

    @JacksonXmlProperty(localName = "house")
    @Valid
    @NotNull
    private HouseCreateRequestDto house;

    @JacksonXmlProperty(localName = "price")
    @NotNull
    @Positive
    @Digits(integer = 12, fraction = 2)
    private BigDecimal price;

    @JacksonXmlProperty(localName = "balconyType")
    @NotNull
    @Enumerated(EnumType.STRING)
    private BalconyType balconyType;

    @JacksonXmlProperty(localName = "walkingMinutesToMetro")
    @NotNull
    @Positive
    private Integer walkingMinutesToMetro;

    @JacksonXmlProperty(localName = "transportMinutesToMetro")
    @NotNull
    @Positive
    private Integer transportMinutesToMetro;
}
