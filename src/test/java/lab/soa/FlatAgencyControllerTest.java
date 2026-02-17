package lab.soa;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import lab.soa.domain.models.BalconyType;
import lab.soa.domain.models.PriceType;
import lab.soa.domain.models.SortType;
import lab.soa.domain.models.TransportType;

@Testcontainers
class FlatAgencyControllerTest extends SpringBootApplicationTest {
    @Test
    void findWithBalcony_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        PriceType priceType = PriceType.CHEAPEST;
        BalconyType balconyType = BalconyType.WITH_BALCONY;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get(
                "/api/v1/flats/agency/find-with-balcony/{priceType}/{balconyType}",
                priceType,
                balconyType
            );

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flat").exists(),
                xpath("/flat/id").string("2"),
                xpath("/flat/name").string("Second Flat"),
                xpath("/flat/coordinates/id").string("2"),
                xpath("/flat/coordinates/x").number(2.0),
                xpath("/flat/coordinates/y").string("100"),
                xpath("/flat/creationDate").exists(),
                xpath("/flat/area").string("2"),
                xpath("/flat/numberOfRooms").string("2"),
                xpath("/flat/height").string("2"),
                xpath("/flat/view").string("BAD"),
                xpath("/flat/transport").string("ENOUGH"),
                xpath("/flat/house/id").string("2"),
                xpath("/flat/house/name").string("Second House"),
                xpath("/flat/house/year").string("2001"),
                xpath("/flat/house/numberOfFlatsOnFloor").string("12"),
                xpath("/flat/price").string("12345678.00"),
                xpath("/flat/balconyType").string("WITH_BALCONY"),
                xpath("/flat/walkingMinutesToMetro").string("1000"),
                xpath("/flat/transportMinutesToMetro").string("100")
            );
    }

    @Test
    void findWithBalcony_ReturnsResponseWithStatusNotFound() throws Exception {
        setupEmptyDb();
        PriceType priceType = PriceType.CHEAPEST;
        BalconyType balconyType = BalconyType.WITH_BALCONY;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get(
                "/api/v1/flats/agency/find-with-balcony/{priceType}/{balconyType}",
                priceType,
                balconyType
            );

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isNotFound(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/error").exists(),
                xpath("/error/message").exists(),
                xpath("/error/violations").exists(),
                xpath("/error/time").exists()
            );
    }

    @Test
    void getOrderedByTimeToMetro_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        TransportType transportType = TransportType.TRANSPORT;
        SortType sortType = SortType.ASC;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get(
                "/api/v1/flats/agency/get-ordered-by-time-to-metro/{transportType}/{sortType}",
                transportType,
                sortType
            );

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(4.0),

                xpath("/flatsPage/flats/flat[1]/id").string("4"),
                xpath("/flatsPage/flats/flat[1]/name").string("Fourth Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("4"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(110.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("1234"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("4"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("4"),
                xpath("/flatsPage/flats/flat[1]/height").string("3"),
                xpath("/flatsPage/flats/flat[1]/view").string("GOOD"),
                xpath("/flatsPage/flats/flat[1]/transport").string(""),
                xpath("/flatsPage/flats/flat[1]/house/id").string("4"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("Fourth House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2003"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("1"),
                xpath("/flatsPage/flats/flat[1]/price").string("0.12"),
                xpath("/flatsPage/flats/flat[1]/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flatsPage/flats/flat[1]/walkingMinutesToMetro").string("10"),
                xpath("/flatsPage/flats/flat[1]/transportMinutesToMetro").string("5"),

                xpath("/flatsPage/flats/flat[2]/id").string("1"),
                xpath("/flatsPage/flats/flat[2]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[2]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[2]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[2]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[2]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[2]/area").string("1"),
                xpath("/flatsPage/flats/flat[2]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[2]/height").string("10"),
                xpath("/flatsPage/flats/flat[2]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[2]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[2]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[2]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[2]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[2]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/flatsPage/flats/flat[2]/price").string("100.01"),
                xpath("/flatsPage/flats/flat[2]/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flatsPage/flats/flat[2]/walkingMinutesToMetro").string("5"),
                xpath("/flatsPage/flats/flat[2]/transportMinutesToMetro").string("10"),

                xpath("/flatsPage/flats/flat[3]/id").string("3"),
                xpath("/flatsPage/flats/flat[3]/name").string("Third Flat"),
                xpath("/flatsPage/flats/flat[3]/coordinates/id").string("3"),
                xpath("/flatsPage/flats/flat[3]/coordinates/x").number(100.0),
                xpath("/flatsPage/flats/flat[3]/coordinates/y").string("200"),
                xpath("/flatsPage/flats/flat[3]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[3]/area").string(""),
                xpath("/flatsPage/flats/flat[3]/numberOfRooms").string("3"),
                xpath("/flatsPage/flats/flat[3]/height").string("3"),
                xpath("/flatsPage/flats/flat[3]/view").string(""),
                xpath("/flatsPage/flats/flat[3]/transport").string("NONE"),
                xpath("/flatsPage/flats/flat[3]/house/id").string("3"),
                xpath("/flatsPage/flats/flat[3]/house/name").string("Third House"),
                xpath("/flatsPage/flats/flat[3]/house/year").string(""),
                xpath("/flatsPage/flats/flat[3]/house/numberOfFlatsOnFloor").string(""),
                xpath("/flatsPage/flats/flat[3]/price").string("10000.12"),
                xpath("/flatsPage/flats/flat[3]/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flatsPage/flats/flat[3]/walkingMinutesToMetro").string("5"),
                xpath("/flatsPage/flats/flat[3]/transportMinutesToMetro").string("10"),

                xpath("/flatsPage/flats/flat[4]/id").string("2"),
                xpath("/flatsPage/flats/flat[4]/name").string("Second Flat"),
                xpath("/flatsPage/flats/flat[4]/coordinates/id").string("2"),
                xpath("/flatsPage/flats/flat[4]/coordinates/x").number(2.0),
                xpath("/flatsPage/flats/flat[4]/coordinates/y").string("100"),
                xpath("/flatsPage/flats/flat[4]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[4]/area").string("2"),
                xpath("/flatsPage/flats/flat[4]/numberOfRooms").string("2"),
                xpath("/flatsPage/flats/flat[4]/height").string("2"),
                xpath("/flatsPage/flats/flat[4]/view").string("BAD"),
                xpath("/flatsPage/flats/flat[4]/transport").string("ENOUGH"),
                xpath("/flatsPage/flats/flat[4]/house/id").string("2"),
                xpath("/flatsPage/flats/flat[4]/house/name").string("Second House"),
                xpath("/flatsPage/flats/flat[4]/house/year").string("2001"),
                xpath("/flatsPage/flats/flat[4]/house/numberOfFlatsOnFloor").string("12"),
                xpath("/flatsPage/flats/flat[4]/price").string("12345678.00"),
                xpath("/flatsPage/flats/flat[4]/balconyType").string("WITH_BALCONY"),
                xpath("/flatsPage/flats/flat[4]/walkingMinutesToMetro").string("1000"),
                xpath("/flatsPage/flats/flat[4]/transportMinutesToMetro").string("100"),

                xpath("/flatsPage/totalElements").string("4"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("4")
            );
    }

    @Test
    void getOrderedByTimeToMetroWithParams_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        TransportType transportType = TransportType.TRANSPORT;
        SortType sortType = SortType.ASC;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get(
                "/api/v1/flats/agency/get-ordered-by-time-to-metro/{transportType}/{sortType}",
                transportType,
                sortType
            )
            .param("page", "1")
            .param("size", "2");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(2.0),

                xpath("/flatsPage/flats/flat[1]/id").string("3"),
                xpath("/flatsPage/flats/flat[1]/name").string("Third Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("3"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(100.0),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("200"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string(""),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("3"),
                xpath("/flatsPage/flats/flat[1]/height").string("3"),
                xpath("/flatsPage/flats/flat[1]/view").string(""),
                xpath("/flatsPage/flats/flat[1]/transport").string("NONE"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("3"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("Third House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string(""),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string(""),
                xpath("/flatsPage/flats/flat[1]/price").string("10000.12"),
                xpath("/flatsPage/flats/flat[1]/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flatsPage/flats/flat[1]/walkingMinutesToMetro").string("5"),
                xpath("/flatsPage/flats/flat[1]/transportMinutesToMetro").string("10"),

                xpath("/flatsPage/flats/flat[2]/id").string("2"),
                xpath("/flatsPage/flats/flat[2]/name").string("Second Flat"),
                xpath("/flatsPage/flats/flat[2]/coordinates/id").string("2"),
                xpath("/flatsPage/flats/flat[2]/coordinates/x").number(2.0),
                xpath("/flatsPage/flats/flat[2]/coordinates/y").string("100"),
                xpath("/flatsPage/flats/flat[2]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[2]/area").string("2"),
                xpath("/flatsPage/flats/flat[2]/numberOfRooms").string("2"),
                xpath("/flatsPage/flats/flat[2]/height").string("2"),
                xpath("/flatsPage/flats/flat[2]/view").string("BAD"),
                xpath("/flatsPage/flats/flat[2]/transport").string("ENOUGH"),
                xpath("/flatsPage/flats/flat[2]/house/id").string("2"),
                xpath("/flatsPage/flats/flat[2]/house/name").string("Second House"),
                xpath("/flatsPage/flats/flat[2]/house/year").string("2001"),
                xpath("/flatsPage/flats/flat[2]/house/numberOfFlatsOnFloor").string("12"),
                xpath("/flatsPage/flats/flat[2]/price").string("12345678.00"),
                xpath("/flatsPage/flats/flat[2]/balconyType").string("WITH_BALCONY"),
                xpath("/flatsPage/flats/flat[2]/walkingMinutesToMetro").string("1000"),
                xpath("/flatsPage/flats/flat[2]/transportMinutesToMetro").string("100"),

                xpath("/flatsPage/totalElements").string("4"),
                xpath("/flatsPage/totalPages").string("2"),
                xpath("/flatsPage/currentPage").string("1"),
                xpath("/flatsPage/pageSize").string("2")
            );
    }
}
