package lab.soa;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lab.soa.domain.models.BalconyType;
import lab.soa.domain.models.Coordinates;
import lab.soa.domain.models.Flat;
import lab.soa.domain.models.House;
import lab.soa.domain.models.Transport;
import lab.soa.domain.models.View;
import lab.soa.domain.repositories.CoordinatesRepository;
import lab.soa.domain.repositories.HouseRepository;
import lab.soa.domain.repositories.flat.FlatRepository;
import lombok.RequiredArgsConstructor;

@Component
@Profile({"dev", "proxy"})
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final CoordinatesRepository coordinatesRepository;
    private final FlatRepository flatRepository;
    private final HouseRepository houseRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (flatRepository.count() > 0) return;

        Coordinates coordinates1 = Coordinates.builder()
            .x(1.1f)
            .y(123L)
            .build();
        Coordinates coordinates2 = Coordinates.builder()
            .x(2.0f)
            .y(100L)
            .build();
        Coordinates coordinates3 = Coordinates.builder()
            .x(100.0f)
            .y(200L)
            .build();
        Coordinates coordinates4 = Coordinates.builder()
            .x(110.1f)
            .y(1234L)
            .build();
        coordinatesRepository.save(coordinates1);
        coordinatesRepository.save(coordinates2);
        coordinatesRepository.save(coordinates3);
        coordinatesRepository.save(coordinates4);
        coordinatesRepository.flush();

        House house1 = House.builder()
            .name("First House")
            .year(2000)
            .numberOfFlatsOnFloor(9)
            .build();
        House house2 = House.builder()
            .name("Second House")
            .year(2001)
            .numberOfFlatsOnFloor(12)
            .build();
        House house3 = House.builder()
            .name("Third House")
            .year(2002)
            .numberOfFlatsOnFloor(8)
            .build();
        House house4 = House.builder()
            .name("Fourth House")
            .year(2003)
            .numberOfFlatsOnFloor(1)
            .build();
        houseRepository.save(house1);
        houseRepository.save(house2);
        houseRepository.save(house3);
        houseRepository.save(house4);
        houseRepository.flush();

        Flat flat1 = Flat.builder()
            .name("First Flat")
            .coordinates(coordinates1)
            .area(1)
            .numberOfRooms(1)
            .height(10)
            .view(View.STREET)
            .transport(Transport.FEW)
            .house(house1)
            .price(new BigDecimal("100.01"))
            .balconyType(BalconyType.WITHOUT_BALCONY)
            .walkingMinutesToMetro(5)
            .transportMinutesToMetro(10)
            .build();
        Flat flat2 = Flat.builder()
            .name("Second Flat")
            .coordinates(coordinates2)
            .area(2)
            .numberOfRooms(2)
            .height(2)
            .view(View.BAD)
            .transport(Transport.ENOUGH)
            .house(house2)
            .price(new BigDecimal("12345678.00"))
            .balconyType(BalconyType.WITH_BALCONY)
            .walkingMinutesToMetro(1000)
            .transportMinutesToMetro(100)
            .build();
        Flat flat3 = Flat.builder()
            .name("Third Flat")
            .coordinates(coordinates3)
            .area(null)
            .numberOfRooms(3)
            .height(3)
            .view(null)
            .transport(Transport.NONE)
            .house(house3)
            .price(new BigDecimal("10000.12"))
            .balconyType(BalconyType.WITHOUT_BALCONY)
            .walkingMinutesToMetro(5)
            .transportMinutesToMetro(10)
            .build();
        Flat flat4 = Flat.builder()
            .name("Fourth Flat")
            .coordinates(coordinates4)
            .area(4)
            .numberOfRooms(4)
            .height(3)
            .view(View.GOOD)
            .transport(null)
            .house(house4)
            .price(new BigDecimal("0.12"))
            .balconyType(BalconyType.WITHOUT_BALCONY)
            .walkingMinutesToMetro(10)
            .transportMinutesToMetro(5)
            .build();
        flatRepository.save(flat1);
        flatRepository.save(flat2);
        flatRepository.save(flat3);
        flatRepository.save(flat4);
        flatRepository.flush();
    }
}
