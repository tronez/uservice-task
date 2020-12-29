package uservice.user.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class NewUserDTO {

    @NotBlank(message = "User's first name can't be empty!")
    private String firstName;
    @NotBlank(message = "User's last name can't be empty!")
    private String lastName;
    @Valid
    @NotEmpty(message = "User has to have at least one email")
    private List<NewEmailDTO> emails;
    @Valid
    @NotEmpty(message = "User has to have at least one phone number")
    private List<NewPhoneNumberDTO> phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<NewEmailDTO> getEmails() {
        return emails;
    }

    public List<NewPhoneNumberDTO> getPhoneNumber() {
        return phoneNumber;
    }
}
