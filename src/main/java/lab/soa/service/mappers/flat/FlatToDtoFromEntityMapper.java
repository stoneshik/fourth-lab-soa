package lab.soa.service.mappers.flat;

import org.springframework.stereotype.Service;

import lab.soa.domain.models.Flat;
import lab.soa.presentation.dto.responses.flat.FlatResponseByIdDto;
import lab.soa.presentation.dto.responses.flat.FlatResponseDto;
import lab.soa.service.mappers.CoordinatesMapper;
import lab.soa.service.mappers.HouseMapper;

@Service
public class FlatToDtoFromEntityMapper {
    private FlatToDtoFromEntityMapper() {}

    public static FlatResponseDto toDtoFromEntity(Flat flatEntity) {
        return FlatResponseDto.builder()
            .id(flatEntity.getId())
            .name(flatEntity.getName())
            .coordinates(
                CoordinatesMapper.toDtoFromEntity(
                    flatEntity.getCoordinates()
                )
            )
            .creationDate(flatEntity.getCreationDate())
            .area(flatEntity.getArea())
            .numberOfRooms(flatEntity.getNumberOfRooms())
            .height(flatEntity.getHeight())
            .view(flatEntity.getView())
            .transport(flatEntity.getTransport())
            .house(
                HouseMapper.toDtoFromEntity(
                    flatEntity.getHouse()
                )
            )
            .price(flatEntity.getPrice())
            .balconyType(flatEntity.getBalconyType())
            .walkingMinutesToMetro(flatEntity.getWalkingMinutesToMetro())
            .transportMinutesToMetro(flatEntity.getTransportMinutesToMetro())
            .build();
    }

    public static FlatResponseByIdDto toByIdDtoFromEntity(Flat flatEntity) {
        return FlatResponseByIdDto.builder()
            .id(flatEntity.getId())
            .name(flatEntity.getName())
            .coordinates(
                CoordinatesMapper.toDtoFromEntity(
                    flatEntity.getCoordinates()
                )
            )
            .creationDate(flatEntity.getCreationDate())
            .area(flatEntity.getArea())
            .numberOfRooms(flatEntity.getNumberOfRooms())
            .height(flatEntity.getHeight())
            .view(flatEntity.getView())
            .transport(flatEntity.getTransport())
            .house(
                HouseMapper.toDtoFromEntity(
                    flatEntity.getHouse()
                )
            )
            .price(flatEntity.getPrice())
            .balconyType(flatEntity.getBalconyType())
            .walkingMinutesToMetro(flatEntity.getWalkingMinutesToMetro())
            .transportMinutesToMetro(flatEntity.getTransportMinutesToMetro())
            .build();
    }
}
