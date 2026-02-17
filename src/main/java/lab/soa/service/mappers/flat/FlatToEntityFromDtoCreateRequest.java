package lab.soa.service.mappers.flat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.soa.domain.models.Coordinates;
import lab.soa.domain.models.Flat;
import lab.soa.domain.models.House;
import lab.soa.presentation.dto.requests.coordinates.CoordinatesCreateRequestDto;
import lab.soa.presentation.dto.requests.flat.FlatRequestCreateDto;
import lab.soa.presentation.dto.requests.house.HouseCreateRequestDto;
import lab.soa.service.services.coordinates.CoordinatesService;
import lab.soa.service.services.house.HouseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlatToEntityFromDtoCreateRequest {
    private final CoordinatesService coordinatesService;
    private final HouseService houseService;

    @Transactional
    public Flat toEntityFromDto(FlatRequestCreateDto flatDto) {
        Coordinates coordinates = createCoordinatesEntityByMusicBandDto(
            flatDto.getCoordinates()
        );
        House house = createHouseEntityByFlatDto(
            flatDto.getHouse()
        );
        return Flat.builder()
            .name(flatDto.getName())
            .coordinates(coordinates)
            .area(flatDto.getArea())
            .numberOfRooms(flatDto.getNumberOfRooms())
            .height(flatDto.getHeight())
            .view(flatDto.getView())
            .transport(flatDto.getTransport())
            .house(house)
            .price(flatDto.getPrice())
            .balconyType(flatDto.getBalconyType())
            .walkingMinutesToMetro(flatDto.getWalkingMinutesToMetro())
            .transportMinutesToMetro(flatDto.getTransportMinutesToMetro())
            .build();
    }

    private Coordinates createCoordinatesEntityByMusicBandDto(CoordinatesCreateRequestDto dto) {
        return coordinatesService.create(
            dto.getX(),
            dto.getY()
        );
    }

    private House createHouseEntityByFlatDto(
        HouseCreateRequestDto dto
    ) {
        return houseService.create(
            dto.getName(),
            dto.getYear(),
            dto.getNumberOfFlatsOnFloor()
        );
    }
}
