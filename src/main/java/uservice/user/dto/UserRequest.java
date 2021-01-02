package uservice.user.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class UserRequest {

    @NotBlank(message = "User's first name can't be empty!")
    private String firstName;
    @NotBlank(message = "User's last name can't be empty!")
    private String lastName;
    @Valid
    @NotEmpty(message = "User has to have at least one email")
    private List<EmailRequest> emails;
    @Valid
    @NotEmpty(message = "User has to have at least one phone number")
    private List<PhoneNumberRequest> phoneNumbers;

    public UserRequest(String firstName, String lastName,
                       List<EmailRequest> emails, List<PhoneNumberRequest> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
    }

    public UserRequest() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<EmailRequest> getEmails() {
        return emails;
    }

    public List<PhoneNumberRequest> getPhoneNumbers() {
        return phoneNumbers;
    }
}
