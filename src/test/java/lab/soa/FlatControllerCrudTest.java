package lab.soa;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class FlatControllerCrudTest extends SpringBootApplicationTest {
    @Test
    void createFlat_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <flat>
            <name>Test Flat</name>
            <coordinates>
                <x>1.1</x>
                <y>123</y>
            </coordinates>
            <area>1</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>Test House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
            <price>100.50</price>
            <balconyType>WITH_BALCONY</balconyType>
            <walkingMinutesToMetro>10</walkingMinutesToMetro>
            <transportMinutesToMetro>5</transportMinutesToMetro>
        </flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/api/v1/flats")
            .contentType("application/xml;charset=UTF-8")
            .content(xmlRequest);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flat").exists(),
                xpath("/flat/id").string("5"),
                xpath("/flat/name").string("Test Flat"),
                xpath("/flat/coordinates/id").string("5"),
                xpath("/flat/coordinates/x").number(1.1),
                xpath("/flat/coordinates/y").string("123"),
                xpath("/flat/creationDate").exists(),
                xpath("/flat/area").string("1"),
                xpath("/flat/numberOfRooms").string("1"),
                xpath("/flat/height").string("10"),
                xpath("/flat/view").string("STREET"),
                xpath("/flat/transport").string("FEW"),
                xpath("/flat/house/id").string("5"),
                xpath("/flat/house/name").string("Test House"),
                xpath("/flat/house/year").string("2000"),
                xpath("/flat/house/numberOfFlatsOnFloor").string("9"),
                xpath("/flat/price").string("100.50"),
                xpath("/flat/balconyType").string("WITH_BALCONY"),
                xpath("/flat/walkingMinutesToMetro").string("10"),
                xpath("/flat/transportMinutesToMetro").string("5")
            );
    }

    @Test
    void createFlat_ReturnsResponseWithStatusUnprocessableEntity() throws Exception {
        setupDb();
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <flat>
            <name>Test Flat</name>
            <coordinates>
                <x>1.1</x>
                <y>123</y>
            </coordinates>
            <area>0</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>Test House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
            <price>100.50</price>
            <balconyType>WITH_BALCONY</balconyType>
            <walkingMinutesToMetro>10</walkingMinutesToMetro>
            <transportMinutesToMetro>5</transportMinutesToMetro>
        </flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .post("/api/v1/flats")
            .contentType("application/xml;charset=UTF-8")
            .content(xmlRequest);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isUnprocessableEntity(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/error").exists(),
                xpath("/error/message").exists(),
                xpath("/error/violations").exists(),
                xpath("/error/violations/violation").exists(),
                xpath("/error/time").exists()
            );
    }

    @Test
    void getFlatById_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        Long id = 1L;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/{id}", id);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flat/id").string("1"),
                xpath("/flat/name").string("First Flat"),
                xpath("/flat/coordinates/id").string("1"),
                xpath("/flat/coordinates/x").number(1.1),
                xpath("/flat/coordinates/y").string("123"),
                xpath("/flat/creationDate").exists(),
                xpath("/flat/area").string("1"),
                xpath("/flat/numberOfRooms").string("1"),
                xpath("/flat/height").string("10"),
                xpath("/flat/view").string("STREET"),
                xpath("/flat/transport").string("FEW"),
                xpath("/flat/house/id").string("1"),
                xpath("/flat/house/name").string("First House"),
                xpath("/flat/house/year").string("2000"),
                xpath("/flat/house/numberOfFlatsOnFloor").string("9"),
                xpath("/flat/price").string("100.01"),
                xpath("/flat/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flat/walkingMinutesToMetro").string("5"),
                xpath("/flat/transportMinutesToMetro").string("10")
            );
    }

    @Test
    void getFlatById_ReturnsResponseWithStatusNotFound() throws Exception {
        setupDb();
        Long id = 100L;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/{id}", id);

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
    void putFlatById_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        Long id = 1L;
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <Flat>
            <name>Updated Flat</name>
            <coordinates>
                <x>100.1</x>
                <y>123</y>
            </coordinates>
            <area>1</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>Updated House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
            <price>100.50</price>
            <balconyType>WITH_BALCONY</balconyType>
            <walkingMinutesToMetro>10</walkingMinutesToMetro>
            <transportMinutesToMetro>5</transportMinutesToMetro>
        </Flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .put("/api/v1/flats/{id}", id)
            .contentType("application/xml;charset=UTF-8")
            .content(xmlRequest);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flat/id").string("1"),
                xpath("/flat/name").string("Updated Flat"),
                xpath("/flat/coordinates/x").number(100.1),
                xpath("/flat/coordinates/y").string("123"),
                xpath("/flat/creationDate").exists(),
                xpath("/flat/area").string("1"),
                xpath("/flat/numberOfRooms").string("1"),
                xpath("/flat/height").string("10"),
                xpath("/flat/view").string("STREET"),
                xpath("/flat/transport").string("FEW"),
                xpath("/flat/house/name").string("Updated House"),
                xpath("/flat/house/year").string("2000"),
                xpath("/flat/house/numberOfFlatsOnFloor").string("9"),
                xpath("/flat/price").string("100.50"),
                xpath("/flat/balconyType").string("WITH_BALCONY"),
                xpath("/flat/walkingMinutesToMetro").string("10"),
                xpath("/flat/transportMinutesToMetro").string("5")
            );
    }

    @Test
    void putFlatById_ReturnsResponseWithStatusUnprocessableEntity() throws Exception {
        setupDb();
        Long id = 1L;
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <Flat>
            <name>Updated Flat</name>
            <coordinates>
                <x>100.1</x>
                <y>123</y>
            </coordinates>
            <area>0</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>First House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
            <price>100.50</price>
            <balconyType>WITH_BALCONY</balconyType>
            <walkingMinutesToMetro>10</walkingMinutesToMetro>
            <transportMinutesToMetro>5</transportMinutesToMetro>
        </Flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .put("/api/v1/flats/{id}", id)
            .contentType("application/xml;charset=UTF-8")
            .content(xmlRequest);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isUnprocessableEntity(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/error").exists(),
                xpath("/error/message").exists(),
                xpath("/error/violations").exists(),
                xpath("/error/violations/violation").exists(),
                xpath("/error/time").exists()
            );
    }

    @Test
    void putFlatById_ReturnsResponseWithStatusNotFound() throws Exception {
        setupDb();
        Long id = 100L;
        String xmlRequest = """
        <?xml version="1.0" encoding="UTF-8"?>
        <Flat>
            <name>Updated Flat</name>
            <coordinates>
                <x>100.1</x>
                <y>123</y>
            </coordinates>
            <area>1</area>
            <numberOfRooms>1</numberOfRooms>
            <height>10</height>
            <view>STREET</view>
            <transport>FEW</transport>
            <house>
                <name>First House</name>
                <year>2000</year>
                <numberOfFlatsOnFloor>9</numberOfFlatsOnFloor>
            </house>
            <price>100.50</price>
            <balconyType>WITH_BALCONY</balconyType>
            <walkingMinutesToMetro>10</walkingMinutesToMetro>
            <transportMinutesToMetro>5</transportMinutesToMetro>
        </Flat>
        """;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .put("/api/v1/flats/{id}", id)
            .contentType("application/xml;charset=UTF-8")
            .content(xmlRequest);

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
    void deleteFlatById_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final Long id = 1L;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats/{id}", id);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteFlatById_ReturnsResponseWithStatusNotFound() throws Exception {
        setupDb();
        final Long id = 100L;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats/{id}", id);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isNotFound()
            );
    }

    @Test
    void deleteOneFlatByFilterHouseName_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final String houseName = "First House";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("houseName", houseName);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteOneFlatByFilterHouseYear_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final String houseYear = "2000";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("houseYear", houseYear);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteOneFlatByFilterNumberOfFlatsOnFloor_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final String numberOfFlatsOnFloor = "9";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("numberOfFlatsOnFloor", numberOfFlatsOnFloor);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteOneFlatByAllFilters_ReturnsResponseWithStatusNoContent() throws Exception {
        setupDb();
        final String houseName = "First House";
        final String houseYear = "2000";
        final String numberOfFlatsOnFloor = "9";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("houseName", houseName)
            .param("houseYear", houseYear)
            .param("numberOfFlatsOnFloor", numberOfFlatsOnFloor);

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isNoContent()
            );
    }

    @Test
    void deleteOneFlatByFilter_ReturnsResponseWithStatusBadRequest() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isBadRequest(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/error").exists(),
                xpath("/error/message").exists(),
                xpath("/error/violations").exists(),
                xpath("/error/time").exists()
            );
    }

    @Test
    void deleteOneFlatByFilter_ReturnsResponseWithStatusNotFound() throws Exception {
        setupDb();
        final String houseName = "Not Found House";
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .delete("/api/v1/flats")
            .param("houseName", houseName);

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
    void getFlatsSumHeight_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/sum/height");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/response").exists(),
                xpath("/response/value").string("18")
            );
    }

    @Test
    void getFlatsZeroSumHeight_ReturnsResponseWithStatusOk() throws Exception {
        setupEmptyDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/sum/height");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/response").exists(),
                xpath("/response/value").string("0")
            );
    }

    @Test
    void getFlatsGroupByHeight_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/group-by/height");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/groupsWrapper").exists(),
                xpath("/groupsWrapper/groups").exists(),
                xpath("count(/groupsWrapper/groups/group)").number(3.0),

                xpath("/groupsWrapper/groups/group[1]").exists(),
                xpath("/groupsWrapper/groups/group[1]/height").string("3"),
                xpath("/groupsWrapper/groups/group[1]/count").string("2"),

                xpath("/groupsWrapper/groups/group[2]").exists(),
                xpath("/groupsWrapper/groups/group[2]/height").string("10"),
                xpath("/groupsWrapper/groups/group[2]/count").string("1"),

                xpath("/groupsWrapper/groups/group[3]").exists(),
                xpath("/groupsWrapper/groups/group[3]/height").string("2"),
                xpath("/groupsWrapper/groups/group[3]/count").string("1")
            );
    }

    @Test
    void getFlatsGroupByHeight_ReturnsResponseWithEmptyListStatusOk() throws Exception {
        setupEmptyDb();;
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats/group-by/height");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/groupsWrapper").exists(),
                xpath("/groupsWrapper/groups").exists(),
                xpath("count(/groupsWrapper/groups/group)").number(0.0)
            );
    }
}
