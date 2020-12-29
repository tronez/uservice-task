package uservice.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import uservice.user.dto.EmailDto;
import uservice.user.dto.NewEmailDTO;
import uservice.user.dto.NewPhoneNumberDTO;
import uservice.user.dto.PhoneNumberDto;
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
    public void testSave() {

        final UserDto responseUser = given()
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

        assertEqualUser(userToBeSaved, responseUser);
    }

    @Test
    @DisplayName("Should return 200 with body containing user with matching id")
    @Order(2)
    void testReturnById() {

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
    @DisplayName("Should return 200 with body containing new email")
    @Order(3)
    void testAddNewMailToUser() {

        final NewEmailDTO newEmailDTO = EmailFactory.createMailForSaving();
        final EmailDto responseEmail = given()
                .port(port)
                .contentType("application/json")
                .body(newEmailDTO)
                .when()
                .post("/api/user/email")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", containsString("/api/user/email"))
                .extract()
                .body().as(EmailDto.class);

        assertEquals(newEmailDTO.getMail(), responseEmail.getMail(), "mail content not matching");
        assertEquals(newEmailDTO.getUserId(), responseEmail.getUserId(), "user id not matching");
    }

    @Test
    @DisplayName("Should return 200 with body containing new phone number")
    @Order(4)
    void testAddNewPhoneNumberToUser() {

        final NewPhoneNumberDTO newPhoneNumberDTO = PhoneNumberFactory.createPhoneNumberForSaving();
        final PhoneNumberDto responseNumber = given()
                .port(port)
                .contentType("application/json")
                .body(newPhoneNumberDTO)
                .when()
                .post("/api/user/phoneNumber")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", containsString("/api/user/phoneNumber"))
                .extract()
                .body().as(PhoneNumberDto.class);

        assertEquals(newPhoneNumberDTO.getNumber(), responseNumber.getNumber(), "numbers not matching");
        assertEquals(newPhoneNumberDTO.getUserId(), responseNumber.getUserId(), "user id not matching");
    }

    @Test
    @DisplayName("Should return 200 with body containing user with matching lastname")
    @Order(5)
    void testReturnByName() {

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
    @Order(6)
    void testDelete() {

        given()
                .port(port)
                .delete("/api/user/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    private void assertEqualUser(UserDto newUser, UserDto retrievedUser) {
        assertEquals(newUser.getFirstName(), retrievedUser.getFirstName());
        assertEquals(newUser.getLastName(), retrievedUser.getLastName());
        assertEquals(newUser.getEmails().size(), retrievedUser.getEmails().size());
        assertEquals(newUser.getPhoneNumber().size(), retrievedUser.getPhoneNumber().size());
    }
}