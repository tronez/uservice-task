package uservice.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import uservice.user.dto.EmailResponse;
import uservice.user.dto.NewEmailRequest;
import uservice.user.dto.NewPhoneNumberRequest;
import uservice.user.dto.PhoneNumberResponse;
import uservice.user.dto.UserResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    private final UserResponse userToBeSaved = UserFactory.createUserForSaving();

    @Test
    @DisplayName("Should return 201 Http status with body and uri pointing to newly created resource")
    @Order(1)
    public void testSave() {

        final UserResponse responseUser = given()
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
                .body().as(UserResponse.class);

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
                .body().as(UserResponse.class);
    }

    @Test
    @DisplayName("Should return 200 with body containing new email")
    @Order(3)
    void testAddNewMailToUser() {

        final NewEmailRequest newEmailRequest = EmailFactory.createMailForSaving();
        final EmailResponse responseEmail = given()
                .port(port)
                .contentType("application/json")
                .body(newEmailRequest)
                .when()
                .post("/api/user/email")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", containsString("/api/user/email"))
                .extract()
                .body().as(EmailResponse.class);

        assertEquals(newEmailRequest.getMail(), responseEmail.getMail(), "mail content not matching");
        assertEquals(newEmailRequest.getUserId(), responseEmail.getUserId(), "user id not matching");
    }

    @Test
    @DisplayName("Should return 200 with body containing new phone number")
    @Order(4)
    void testAddNewPhoneNumberToUser() {

        final NewPhoneNumberRequest newPhoneNumberRequest = PhoneNumberFactory.createPhoneNumberForSaving();
        final PhoneNumberResponse responseNumber = given()
                .port(port)
                .contentType("application/json")
                .body(newPhoneNumberRequest)
                .when()
                .post("/api/user/phoneNumber")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", containsString("/api/user/phoneNumber"))
                .extract()
                .body().as(PhoneNumberResponse.class);

        assertEquals(newPhoneNumberRequest.getNumber(), responseNumber.getNumber(), "numbers not matching");
        assertEquals(newPhoneNumberRequest.getUserId(), responseNumber.getUserId(), "user id not matching");
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
                .body().as(UserResponse.class);
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

    private void assertEqualUser(UserResponse newUser, UserResponse retrievedUser) {
        assertEquals(newUser.getFirstName(), retrievedUser.getFirstName());
        assertEquals(newUser.getLastName(), retrievedUser.getLastName());
        assertEquals(newUser.getEmails().size(), retrievedUser.getEmails().size());
        assertEquals(newUser.getPhoneNumber().size(), retrievedUser.getPhoneNumber().size());
    }
}