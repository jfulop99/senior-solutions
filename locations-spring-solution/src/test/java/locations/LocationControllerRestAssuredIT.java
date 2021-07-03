package locations;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerRestAssuredIT {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.requestSpecification =
                given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    @Test
    void getLocationsTest() {
        with()
                .body(new CreateLocationCommand("Budapest", 47.1312, 19.2322))
                .post("locations")
                .then()
                .statusCode(201)
                .body("name", equalTo("Budapest"))
                .log();

        with()
                .get("locations")
                .then()
                .statusCode(200)
                .body("[3].name", equalTo("Budapest"))
                .body("size()", equalTo(4));
    }
}
