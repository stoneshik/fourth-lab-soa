package lab.soa;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.soa.domain.models.BalconyType;
import lab.soa.domain.models.Coordinates;
import lab.soa.domain.models.Flat;
import lab.soa.domain.models.House;
import lab.soa.domain.models.Transport;
import lab.soa.domain.models.View;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
abstract class SpringBootApplicationTest {
    private static LocalDateTime firstFlatCreationDate;

    @Container
    static final PostgreSQLContainer<?> postgresSqlContainer;

    static {
        postgresSqlContainer = new PostgreSQLContainer<>("postgres:16.4")
                .withReuse(false)
                .withDatabaseName("is_service");
        postgresSqlContainer.start();
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresSqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresSqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresSqlContainer::getPassword);
    }

    @AfterAll
    void stopContainers() {
        if (postgresSqlContainer.isRunning()) {
            postgresSqlContainer.stop();
        }
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @PersistenceContext
    private EntityManager entityManager;

    protected void setupDb() {
        new TransactionTemplate(transactionManager).execute(
            status -> {
                clearDb();
                createEntitiesInDb();
                forceWritingToDb();
                return null;
            }
        );
    }

    protected void setupEmptyDb() {
        new TransactionTemplate(transactionManager).execute(
            status -> {
                clearDb();
                forceWritingToDb();
                return null;
            }
        );
    }

    private void clearDb() {
        entityManager.createNativeQuery(
            """
            TRUNCATE
                coordinates,
                flats,
                houses
            RESTART IDENTITY CASCADE
            """
        ).executeUpdate();
        entityManager.clear();
    }

    protected void createEntitiesInDb() {
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
        entityManager.persist(coordinates1);
        entityManager.persist(coordinates2);
        entityManager.persist(coordinates3);
        entityManager.persist(coordinates4);

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
            .year(null)
            .numberOfFlatsOnFloor(null)
            .build();
        House house4 = House.builder()
            .name("Fourth House")
            .year(2003)
            .numberOfFlatsOnFloor(1)
            .build();
        entityManager.persist(house1);
        entityManager.persist(house2);
        entityManager.persist(house3);
        entityManager.persist(house4);

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
        entityManager.persist(flat1);
        entityManager.persist(flat2);
        entityManager.persist(flat3);
        entityManager.persist(flat4);

        firstFlatCreationDate = flat1.getCreationDate();
    }

    private void forceWritingToDb() {
        entityManager.flush();
        entityManager.clear();
    }

    public static LocalDateTime getFirstFlatCreationDate() {
        return firstFlatCreationDate;
    }
}
