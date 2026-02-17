package lab.soa.service.services.house;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import lab.soa.domain.models.House;
import lab.soa.domain.repositories.HouseRepository;
import lab.soa.presentation.dto.requests.house.HouseUpdateRequestDto;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    @Transactional
    public House create(String name, Integer year, Integer numberOfFlatsOnFloor) {
        House house = House.builder()
            .name(name)
            .year(year)
            .numberOfFlatsOnFloor(numberOfFlatsOnFloor)
            .build();
        House savedHouse = houseRepository.save(house);
        houseRepository.flush();
        return savedHouse;
    }

    @Transactional
    public House update(House house, HouseUpdateRequestDto updateRequestDto) {
        House updatedHouse = house.toBuilder()
            .name(updateRequestDto.getName())
            .year(updateRequestDto.getYear())
            .numberOfFlatsOnFloor(updateRequestDto.getNumberOfFlatsOnFloor())
            .build();
        House savedHouse = houseRepository.save(updatedHouse);
        houseRepository.flush();
        return savedHouse;
    }
}
