package uservice.user.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UserDTO {

    private Long id;
    @NotBlank(message = "User's first name can't be empty!")
    private String firstName;
    @NotBlank(message = "User's last name can't be empty!")
    private String lastName;
    @Valid
    @NotEmpty(message = "User has to have at least one email")
    private Set<EmailDTO> emails;
    @Valid
    @NotEmpty(message = "User has to have at least one phone number")
    private Set<PhoneNumberDTO> phoneNumber;

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Set<EmailDTO> getEmails() {
        return emails;
    }

    public Set<PhoneNumberDTO> getPhoneNumber() {
        return phoneNumber;
    }

    public UserDTO(Long id, String firstName, String lastName, Set<EmailDTO> emails, Set<PhoneNumberDTO> phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emails = emails;
        this.phoneNumber = phoneNumber;
    }
}
