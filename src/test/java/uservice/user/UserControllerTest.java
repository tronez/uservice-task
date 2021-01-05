package uservice.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import uservice.user.dto.EmailDTO;
import uservice.user.dto.NewEmailRequest;
import uservice.user.dto.NewPhoneNumberRequest;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserRequest;
import uservice.user.dto.UserResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    private final UserRequest userToBeSaved = UserFactory.createUserForSaving();

    @Test
    @DisplayName("Should return 201 Http status with body and uri pointing to newly created user resource")
    @Order(1)
    public void testSave() {

        final UserResponse responseUser = given()
                .port(port)
                .contentType("application/json")
                .body(userToBeSaved)
                .when()
                .post("/api/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", containsString("/api/users/"))
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
                .get("/api/users/1")
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
        final EmailDTO responseEmail = given()
                .port(port)
                .contentType("application/json")
                .body(newEmailRequest)
                .when()
                .post("/api/emails")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", containsString("/api/emails"))
                .extract()
                .body().as(EmailDTO.class);

        assertEquals(newEmailRequest.getEmail(), responseEmail.getEmail(), "mail content not matching");
        assertEquals(newEmailRequest.getUserId(), responseEmail.getUserId(), "user id not matching");
    }

    @Test
    @DisplayName("Should return 200 with body containing new phone number")
    @Order(4)
    void testAddNewPhoneNumberToUser() {

        final NewPhoneNumberRequest newPhoneNumberRequest = PhoneNumberFactory.createPhoneNumberForSaving();
        final PhoneNumberDTO responseNumber = given()
                .port(port)
                .contentType("application/json")
                .body(newPhoneNumberRequest)
                .when()
                .post("/api/phone-numbers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .header("location", containsString("/api/phone-numbers"))
                .extract()
                .body().as(PhoneNumberDTO.class);

        assertEquals(newPhoneNumberRequest.getPhoneNumber(), responseNumber.getPhoneNumber(), "numbers not matching");
        assertEquals(newPhoneNumberRequest.getUserId(), responseNumber.getUserId(), "user id not matching");
    }

    @Test
    @DisplayName("Should return 200 with body containing user with matching last name")
    @Order(5)
    void testReturnByName() {

        given()
                .port(port)
                .get("/api/users?last-name=" + userToBeSaved.getLastName())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().as(UserResponse.class);
    }

    @Test
    @DisplayName("Should return 201 Http status with body and uri pointing to newly created email resource")
    @Order(6)
    void testUpdateEmail() {

        final EmailDTO emailToUpdate = EmailFactory.createMailForUpdate();

        given()
                .port(port)
                .contentType("application/json")
                .body(emailToUpdate)
                .when()
                .put("/api/emails/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().as(EmailDTO.class);
    }

    @Test
    @DisplayName("Should return 201 Http status with body and uri pointing to newly created phone number resource")
    @Order(7)
    void testUpdatePhoneNumber() {

        final PhoneNumberDTO numberToUpdate = PhoneNumberFactory.createPhoneNumberForUpdate();

        given()
                .port(port)
                .contentType("application/json")
                .body(numberToUpdate)
                .when()
                .patch("/api/phone-numbers/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().as(PhoneNumberDTO.class);
    }

    @Test
    @DisplayName("Should return 204 on resource deletion")
    @Order(8)
    void testDelete() {

        given()
                .port(port)
                .delete("/api/users/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private void assertEqualUser(UserRequest newUser, UserResponse retrievedUser) {
        assertEquals(newUser.getFirstName(), retrievedUser.getFirstName());
        assertEquals(newUser.getLastName(), retrievedUser.getLastName());
    }
}