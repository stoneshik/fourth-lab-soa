package lab.soa;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class FlatControllerGetListTest extends SpringBootApplicationTest {
    @Test
    void getEmptyListFlats_ReturnsResponseWithStatusOk() throws Exception {
        setupEmptyDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(0.0),
                xpath("/flatsPage/totalElements").string("0"),
                xpath("/flatsPage/totalPages").string("0"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("0")
            );
    }

    @Test
    void getListFlats_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(4.0),

                xpath("/flatsPage/flats/flat[1]/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("1"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[1]/height").string("10"),
                xpath("/flatsPage/flats/flat[1]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[1]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("9"),

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

                xpath("/flatsPage/flats/flat[4]/id").string("4"),
                xpath("/flatsPage/flats/flat[4]/name").string("Fourth Flat"),
                xpath("/flatsPage/flats/flat[4]/coordinates/id").string("4"),
                xpath("/flatsPage/flats/flat[4]/coordinates/x").number(110.1),
                xpath("/flatsPage/flats/flat[4]/coordinates/y").string("1234"),
                xpath("/flatsPage/flats/flat[4]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[4]/area").string("4"),
                xpath("/flatsPage/flats/flat[4]/numberOfRooms").string("4"),
                xpath("/flatsPage/flats/flat[4]/height").string("3"),
                xpath("/flatsPage/flats/flat[4]/view").string("GOOD"),
                xpath("/flatsPage/flats/flat[4]/transport").string(""),
                xpath("/flatsPage/flats/flat[4]/house/id").string("4"),
                xpath("/flatsPage/flats/flat[4]/house/name").string("Fourth House"),
                xpath("/flatsPage/flats/flat[4]/house/year").string("2003"),
                xpath("/flatsPage/flats/flat[4]/house/numberOfFlatsOnFloor").string("1"),

                xpath("/flatsPage/totalElements").string("4"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("4")
            );
    }

    @Test
    void getListFlatsByPageParams_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,desc")
            .param("page", "0")
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

                xpath("/flatsPage/flats/flat[2]/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/name").string("Third Flat"),
                xpath("/flatsPage/flats/flat[2]/coordinates/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/coordinates/x").number(100.0),
                xpath("/flatsPage/flats/flat[2]/coordinates/y").string("200"),
                xpath("/flatsPage/flats/flat[2]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[2]/area").string(""),
                xpath("/flatsPage/flats/flat[2]/numberOfRooms").string("3"),
                xpath("/flatsPage/flats/flat[2]/height").string("3"),
                xpath("/flatsPage/flats/flat[2]/view").string(""),
                xpath("/flatsPage/flats/flat[2]/transport").string("NONE"),
                xpath("/flatsPage/flats/flat[2]/house/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/house/name").string("Third House"),
                xpath("/flatsPage/flats/flat[2]/house/year").string(""),
                xpath("/flatsPage/flats/flat[2]/house/numberOfFlatsOnFloor").string(""),

                xpath("/flatsPage/totalElements").string("4"),
                xpath("/flatsPage/totalPages").string("2"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("2")
            );
    }

    @Test
    void getListFlatsByFilterEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "name(eq)First Flat");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(1.0),

                xpath("/flatsPage/flats/flat[1]/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("1"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[1]/height").string("10"),
                xpath("/flatsPage/flats/flat[1]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[1]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("9"),

                xpath("/flatsPage/totalElements").string("1"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterNotEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x(ne)1.1");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(3.0),

                xpath("/flatsPage/flats/flat[1]/id").string("2"),
                xpath("/flatsPage/flats/flat[1]/name").string("Second Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("2"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(2.0),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("100"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("2"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("2"),
                xpath("/flatsPage/flats/flat[1]/height").string("2"),
                xpath("/flatsPage/flats/flat[1]/view").string("BAD"),
                xpath("/flatsPage/flats/flat[1]/transport").string("ENOUGH"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("2"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("Second House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2001"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/flatsPage/flats/flat[2]/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/name").string("Third Flat"),
                xpath("/flatsPage/flats/flat[2]/coordinates/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/coordinates/x").number(100.0),
                xpath("/flatsPage/flats/flat[2]/coordinates/y").string("200"),
                xpath("/flatsPage/flats/flat[2]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[2]/area").string(""),
                xpath("/flatsPage/flats/flat[2]/numberOfRooms").string("3"),
                xpath("/flatsPage/flats/flat[2]/height").string("3"),
                xpath("/flatsPage/flats/flat[2]/view").string(""),
                xpath("/flatsPage/flats/flat[2]/transport").string("NONE"),
                xpath("/flatsPage/flats/flat[2]/house/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/house/name").string("Third House"),
                xpath("/flatsPage/flats/flat[2]/house/year").string(""),
                xpath("/flatsPage/flats/flat[2]/house/numberOfFlatsOnFloor").string(""),

                xpath("/flatsPage/flats/flat[3]/id").string("4"),
                xpath("/flatsPage/flats/flat[3]/name").string("Fourth Flat"),
                xpath("/flatsPage/flats/flat[3]/coordinates/id").string("4"),
                xpath("/flatsPage/flats/flat[3]/coordinates/x").number(110.1),
                xpath("/flatsPage/flats/flat[3]/coordinates/y").string("1234"),
                xpath("/flatsPage/flats/flat[3]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[3]/area").string("4"),
                xpath("/flatsPage/flats/flat[3]/numberOfRooms").string("4"),
                xpath("/flatsPage/flats/flat[3]/height").string("3"),
                xpath("/flatsPage/flats/flat[3]/view").string("GOOD"),
                xpath("/flatsPage/flats/flat[3]/transport").string(""),
                xpath("/flatsPage/flats/flat[3]/house/id").string("4"),
                xpath("/flatsPage/flats/flat[3]/house/name").string("Fourth House"),
                xpath("/flatsPage/flats/flat[3]/house/year").string("2003"),
                xpath("/flatsPage/flats/flat[3]/house/numberOfFlatsOnFloor").string("1"),

                xpath("/flatsPage/totalElements").string("3"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("3")
            );
    }

    @Test
    void getListFlatsByFilterGreaterThan_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x(gt)1.1");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(3.0),

                xpath("/flatsPage/flats/flat[1]/id").string("2"),
                xpath("/flatsPage/flats/flat[1]/name").string("Second Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("2"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(2.0),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("100"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("2"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("2"),
                xpath("/flatsPage/flats/flat[1]/height").string("2"),
                xpath("/flatsPage/flats/flat[1]/view").string("BAD"),
                xpath("/flatsPage/flats/flat[1]/transport").string("ENOUGH"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("2"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("Second House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2001"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("12"),

                xpath("/flatsPage/flats/flat[2]/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/name").string("Third Flat"),
                xpath("/flatsPage/flats/flat[2]/coordinates/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/coordinates/x").number(100.0),
                xpath("/flatsPage/flats/flat[2]/coordinates/y").string("200"),
                xpath("/flatsPage/flats/flat[2]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[2]/area").string(""),
                xpath("/flatsPage/flats/flat[2]/numberOfRooms").string("3"),
                xpath("/flatsPage/flats/flat[2]/height").string("3"),
                xpath("/flatsPage/flats/flat[2]/view").string(""),
                xpath("/flatsPage/flats/flat[2]/transport").string("NONE"),
                xpath("/flatsPage/flats/flat[2]/house/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/house/name").string("Third House"),
                xpath("/flatsPage/flats/flat[2]/house/year").string(""),
                xpath("/flatsPage/flats/flat[2]/house/numberOfFlatsOnFloor").string(""),

                xpath("/flatsPage/flats/flat[3]/id").string("4"),
                xpath("/flatsPage/flats/flat[3]/name").string("Fourth Flat"),
                xpath("/flatsPage/flats/flat[3]/coordinates/id").string("4"),
                xpath("/flatsPage/flats/flat[3]/coordinates/x").number(110.1),
                xpath("/flatsPage/flats/flat[3]/coordinates/y").string("1234"),
                xpath("/flatsPage/flats/flat[3]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[3]/area").string("4"),
                xpath("/flatsPage/flats/flat[3]/numberOfRooms").string("4"),
                xpath("/flatsPage/flats/flat[3]/height").string("3"),
                xpath("/flatsPage/flats/flat[3]/view").string("GOOD"),
                xpath("/flatsPage/flats/flat[3]/transport").string(""),
                xpath("/flatsPage/flats/flat[3]/house/id").string("4"),
                xpath("/flatsPage/flats/flat[3]/house/name").string("Fourth House"),
                xpath("/flatsPage/flats/flat[3]/house/year").string("2003"),
                xpath("/flatsPage/flats/flat[3]/house/numberOfFlatsOnFloor").string("1"),

                xpath("/flatsPage/totalElements").string("3"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("3")
            );
    }

    @Test
    void getListFlatsByFilterLessThan_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x(lt)1.1");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(0.0),
                xpath("/flatsPage/totalElements").string("0"),
                xpath("/flatsPage/totalPages").string("0"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("0")
            );
    }

    @Test
    void getListFlatsByFilterGreaterThanEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x(gte)1.1");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(4.0),

                xpath("/flatsPage/flats/flat[1]/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("1"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[1]/height").string("10"),
                xpath("/flatsPage/flats/flat[1]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[1]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("9"),

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

                xpath("/flatsPage/flats/flat[4]/id").string("4"),
                xpath("/flatsPage/flats/flat[4]/name").string("Fourth Flat"),
                xpath("/flatsPage/flats/flat[4]/coordinates/id").string("4"),
                xpath("/flatsPage/flats/flat[4]/coordinates/x").number(110.1),
                xpath("/flatsPage/flats/flat[4]/coordinates/y").string("1234"),
                xpath("/flatsPage/flats/flat[4]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[4]/area").string("4"),
                xpath("/flatsPage/flats/flat[4]/numberOfRooms").string("4"),
                xpath("/flatsPage/flats/flat[4]/height").string("3"),
                xpath("/flatsPage/flats/flat[4]/view").string("GOOD"),
                xpath("/flatsPage/flats/flat[4]/transport").string(""),
                xpath("/flatsPage/flats/flat[4]/house/id").string("4"),
                xpath("/flatsPage/flats/flat[4]/house/name").string("Fourth House"),
                xpath("/flatsPage/flats/flat[4]/house/year").string("2003"),
                xpath("/flatsPage/flats/flat[4]/house/numberOfFlatsOnFloor").string("1"),

                xpath("/flatsPage/totalElements").string("4"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("4")
            );
    }

    @Test
    void getListFlatsByFilterLessThanEquals_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x(lte)1.1");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(1.0),

                xpath("/flatsPage/flats/flat[1]/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("1"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[1]/height").string("10"),
                xpath("/flatsPage/flats/flat[1]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[1]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("9"),

                xpath("/flatsPage/totalElements").string("1"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterInterval_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x(interval)1.1_2.0");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(0.0),

                xpath("/flatsPage/totalElements").string("0"),
                xpath("/flatsPage/totalPages").string("0"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("0")
            );
    }

    @Test
    void getListFlatsByFilterRange_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x(range)1.1_2.0");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(2.0),

                xpath("/flatsPage/flats/flat[1]/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("1"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[1]/height").string("10"),
                xpath("/flatsPage/flats/flat[1]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[1]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("9"),

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

                xpath("/flatsPage/totalElements").string("2"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("2")
            );
    }

    @Test
    void getListFlatsByFiltersEqualsAndGreaterThan_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x(gte)1.1")
            .param("filter", "coordinates.y(lt)200");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(2.0),

                xpath("/flatsPage/flats/flat[1]/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("1"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[1]/height").string("10"),
                xpath("/flatsPage/flats/flat[1]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[1]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("9"),

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

                xpath("/flatsPage/totalElements").string("2"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("2")
            );
    }

    @Test
    void getListFlatsByFiltersEquals_ReturnsResponseWithStatusBadRequest() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates(eq)1.1");

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
    void getListFlatsByFilterEqualsId_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "id(eq)1");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsCoordinatesId_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.id(eq)1");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsCoordinatesX_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.x(eq)1.1");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsCoordinatesY_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "coordinates.y(eq)123");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsCreationDate_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        LocalDateTime firstFlatCreationDate = SpringBootApplicationTest.getFirstFlatCreationDate();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "creationDate(eq)" + firstFlatCreationDate.toString());

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsArea_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "area(eq)1");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsNumberOfRooms_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "numberOfRooms(eq)1");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsHeight_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "height(eq)10");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsView_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "view(eq)STREET");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsTransport_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "transport(eq)FEW");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsHouseId_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "house.name(eq)First House");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsHouseYear_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "house.year(eq)2000");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsPrice_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "price(eq)100.01");

        performAndCheckGetListFlatsRequestByFilterEquals(
            requestBuilder
        );
    }

    @Test
    void getListFlatsByFilterEqualsBalconyType_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "balconyType(eq)WITH_BALCONY");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(1.0),

                xpath("/flatsPage/flats/flat[1]/id").string("2"),
                xpath("/flatsPage/flats/flat[1]/name").string("Second Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("2"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(2.0),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("100"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("2"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("2"),
                xpath("/flatsPage/flats/flat[1]/height").string("2"),
                xpath("/flatsPage/flats/flat[1]/view").string("BAD"),
                xpath("/flatsPage/flats/flat[1]/transport").string("ENOUGH"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("2"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("Second House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2001"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("12"),
                xpath("/flatsPage/flats/flat[1]/price").string("12345678.00"),
                xpath("/flatsPage/flats/flat[1]/balconyType").string("WITH_BALCONY"),
                xpath("/flatsPage/flats/flat[1]/walkingMinutesToMetro").string("1000"),
                xpath("/flatsPage/flats/flat[1]/transportMinutesToMetro").string("100"),

                xpath("/flatsPage/totalElements").string("1"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("1")
            );
    }

    @Test
    void getListFlatsByFilterEqualsWalkingMinutesToMetro_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "walkingMinutesToMetro(eq)5");
        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(2.0),

                xpath("/flatsPage/flats/flat[1]/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("1"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[1]/height").string("10"),
                xpath("/flatsPage/flats/flat[1]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[1]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/flatsPage/flats/flat[1]/price").string("100.01"),
                xpath("/flatsPage/flats/flat[1]/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flatsPage/flats/flat[1]/walkingMinutesToMetro").string("5"),
                xpath("/flatsPage/flats/flat[1]/transportMinutesToMetro").string("10"),

                xpath("/flatsPage/flats/flat[2]/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/name").string("Third Flat"),
                xpath("/flatsPage/flats/flat[2]/coordinates/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/coordinates/x").number(100.0),
                xpath("/flatsPage/flats/flat[2]/coordinates/y").string("200"),
                xpath("/flatsPage/flats/flat[2]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[2]/area").string(""),
                xpath("/flatsPage/flats/flat[2]/numberOfRooms").string("3"),
                xpath("/flatsPage/flats/flat[2]/height").string("3"),
                xpath("/flatsPage/flats/flat[2]/view").string(""),
                xpath("/flatsPage/flats/flat[2]/transport").string("NONE"),
                xpath("/flatsPage/flats/flat[2]/house/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/house/name").string("Third House"),
                xpath("/flatsPage/flats/flat[2]/house/year").string(""),
                xpath("/flatsPage/flats/flat[2]/house/numberOfFlatsOnFloor").string(""),
                xpath("/flatsPage/flats/flat[2]/price").string("10000.12"),
                xpath("/flatsPage/flats/flat[2]/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flatsPage/flats/flat[2]/walkingMinutesToMetro").string("5"),
                xpath("/flatsPage/flats/flat[2]/transportMinutesToMetro").string("10"),

                xpath("/flatsPage/totalElements").string("2"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("2")
            );
    }

    @Test
    void getListFlatsByFilterEqualsTransportMinutesToMetro_ReturnsResponseWithStatusOk() throws Exception {
        setupDb();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/v1/flats")
            .param("sort", "id,asc")
            .param("page", "0")
            .param("size", "4")
            .param("filter", "transportMinutesToMetro(eq)10");

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(2.0),

                xpath("/flatsPage/flats/flat[1]/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("1"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[1]/height").string("10"),
                xpath("/flatsPage/flats/flat[1]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[1]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/flatsPage/flats/flat[1]/price").string("100.01"),
                xpath("/flatsPage/flats/flat[1]/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flatsPage/flats/flat[1]/walkingMinutesToMetro").string("5"),
                xpath("/flatsPage/flats/flat[1]/transportMinutesToMetro").string("10"),

                xpath("/flatsPage/flats/flat[2]/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/name").string("Third Flat"),
                xpath("/flatsPage/flats/flat[2]/coordinates/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/coordinates/x").number(100.0),
                xpath("/flatsPage/flats/flat[2]/coordinates/y").string("200"),
                xpath("/flatsPage/flats/flat[2]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[2]/area").string(""),
                xpath("/flatsPage/flats/flat[2]/numberOfRooms").string("3"),
                xpath("/flatsPage/flats/flat[2]/height").string("3"),
                xpath("/flatsPage/flats/flat[2]/view").string(""),
                xpath("/flatsPage/flats/flat[2]/transport").string("NONE"),
                xpath("/flatsPage/flats/flat[2]/house/id").string("3"),
                xpath("/flatsPage/flats/flat[2]/house/name").string("Third House"),
                xpath("/flatsPage/flats/flat[2]/house/year").string(""),
                xpath("/flatsPage/flats/flat[2]/house/numberOfFlatsOnFloor").string(""),
                xpath("/flatsPage/flats/flat[2]/price").string("10000.12"),
                xpath("/flatsPage/flats/flat[2]/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flatsPage/flats/flat[2]/walkingMinutesToMetro").string("5"),
                xpath("/flatsPage/flats/flat[2]/transportMinutesToMetro").string("10"),

                xpath("/flatsPage/totalElements").string("2"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("2")
            );
    }

    private void performAndCheckGetListFlatsRequestByFilterEquals(
        MockHttpServletRequestBuilder requestBuilder
    ) throws Exception {
        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("application/xml;charset=UTF-8"),
                xpath("/flatsPage").exists(),
                xpath("/flatsPage/flats").exists(),
                xpath("count(/flatsPage/flats/flat)").number(1.0),

                xpath("/flatsPage/flats/flat[1]/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/name").string("First Flat"),
                xpath("/flatsPage/flats/flat[1]/coordinates/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/coordinates/x").number(1.1),
                xpath("/flatsPage/flats/flat[1]/coordinates/y").string("123"),
                xpath("/flatsPage/flats/flat[1]/creationDate").exists(),
                xpath("/flatsPage/flats/flat[1]/area").string("1"),
                xpath("/flatsPage/flats/flat[1]/numberOfRooms").string("1"),
                xpath("/flatsPage/flats/flat[1]/height").string("10"),
                xpath("/flatsPage/flats/flat[1]/view").string("STREET"),
                xpath("/flatsPage/flats/flat[1]/transport").string("FEW"),
                xpath("/flatsPage/flats/flat[1]/house/id").string("1"),
                xpath("/flatsPage/flats/flat[1]/house/name").string("First House"),
                xpath("/flatsPage/flats/flat[1]/house/year").string("2000"),
                xpath("/flatsPage/flats/flat[1]/house/numberOfFlatsOnFloor").string("9"),
                xpath("/flatsPage/flats/flat[1]/price").string("100.01"),
                xpath("/flatsPage/flats/flat[1]/balconyType").string("WITHOUT_BALCONY"),
                xpath("/flatsPage/flats/flat[1]/walkingMinutesToMetro").string("5"),
                xpath("/flatsPage/flats/flat[1]/transportMinutesToMetro").string("10"),

                xpath("/flatsPage/totalElements").string("1"),
                xpath("/flatsPage/totalPages").string("1"),
                xpath("/flatsPage/currentPage").string("0"),
                xpath("/flatsPage/pageSize").string("1")
            );
    }
}
