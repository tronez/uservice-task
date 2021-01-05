package uservice.user.dto;

import javax.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "User's first name can't be empty!")
    private String firstName;
    @NotBlank(message = "User's last name can't be empty!")
    private String lastName;

    public UserRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserRequest() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
