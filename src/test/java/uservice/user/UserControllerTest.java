package uservice.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import uservice.user.dto.UserDto;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    private final UserDto userToBeSaved = UserFactory.createUserForSaving();

    @Test
    @DisplayName("Should return 201 Http status with body and uri pointing to newly created resource")
    @Order(1)
    public void testSave1() {

        final UserDto returnedUser = given()
                .port(port)
                .contentType("application/json")
                .body(userToBeSaved)
                .when()
                .post("/api/user")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", containsString("/api/user/"))
                .extract()
                .body().as(UserDto.class);

        assertEqualUser(userToBeSaved, returnedUser);
    }

    @Test
    @DisplayName("Should return 200 with body containing user with matching id")
    @Order(2)
    void testReturnById1() {

        given()
                .port(port)
                .get("/api/user/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().as(UserDto.class);
    }

    @Test
    @DisplayName("Should return 200 with body containing user with matching lastname")
    @Order(3)
    void testReturnByName1() {

        given()
                .port(port)
                .get("/api/user?lastname=" + userToBeSaved.getLastName())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value()).extract()
                .body().as(UserDto.class);
    }

    @Test
    @DisplayName("Should return 204 on resource deletion")
    @Order(4)
    void testDelete1() {

        given()
                .port(port)
                .delete("/api/user/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private void assertEqualUser(UserDto newUser, UserDto retrievedUser) {
        assertEquals(newUser.getFirstName(), retrievedUser.getFirstName());
        assertEquals(newUser.getLastName(), retrievedUser.getLastName());
    }
}