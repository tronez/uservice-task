package uservice.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import uservice.user.dto.UserDto;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    final private UserDto userToBeSaved = UserFactory.createUserForSaving();

    @Test
    @DisplayName("Should return 201 Http status with body and uri pointing to newly created resource")
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
    void testReturnById1() {

        given()
                .port(port)
                .get("/api/user/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should return 200 with body containing user with matching lastname")
    void testReturnByName1() {

        given()
                .port(port)
                .get("/api/user?lastname=" + userToBeSaved.getLastName())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should return 204")
    void testDelete() {

        given()
                .port(port)
                .delete("/api/user/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private void assertEqualUser(UserDto newUser, UserDto retrievedUser) {
        assertEquals(newUser.getFirstName(), newUser.getFirstName());
        assertEquals(newUser.getLastName(), newUser.getLastName());
    }
}