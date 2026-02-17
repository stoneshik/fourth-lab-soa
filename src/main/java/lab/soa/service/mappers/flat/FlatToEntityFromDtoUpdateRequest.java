package lab.soa.service.mappers.flat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.soa.domain.models.Coordinates;
import lab.soa.domain.models.Flat;
import lab.soa.domain.models.House;
import lab.soa.presentation.dto.requests.flat.FlatRequestUpdateDto;
import lab.soa.service.services.coordinates.CoordinatesService;
import lab.soa.service.services.house.HouseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlatToEntityFromDtoUpdateRequest {
    private final CoordinatesService coordinatesService;
    private final HouseService houseService;

    @Transactional
    public Flat toEntityFromDto(
        FlatRequestUpdateDto flatDto,
        Flat foundFlat
    ) {
        Coordinates updatedCoordinates = coordinatesService.update(
            foundFlat.getCoordinates(),
            flatDto.getCoordinates()
        );
        House updatedHouse = houseService.update(
            foundFlat.getHouse(),
            flatDto.getHouse()
        );
        return foundFlat.toBuilder()
            .name(flatDto.getName())
            .coordinates(updatedCoordinates)
            .area(flatDto.getArea())
            .numberOfRooms(flatDto.getNumberOfRooms())
            .height(flatDto.getHeight())
            .view(flatDto.getView())
            .transport(flatDto.getTransport())
            .house(updatedHouse)
            .price(flatDto.getPrice())
            .balconyType(flatDto.getBalconyType())
            .walkingMinutesToMetro(flatDto.getWalkingMinutesToMetro())
            .transportMinutesToMetro(flatDto.getTransportMinutesToMetro())
            .build();
    }
}
