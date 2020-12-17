package uservice.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class UserDto {

    private Long id;
    @NotBlank(message = "User's first name can't be empty!")
    private String firstName;
    @NotBlank(message = "User's last name can't be empty!")
    private String lastName;
    @NotEmpty(message = "User has to have at least one email")
    private Set<EmailDto> emails;
    @NotEmpty(message = "User has to have at least one phone number")
    private Set<PhoneNumberDto> phoneNumber;

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Set<EmailDto> getEmails() {
        return emails;
    }

    public Set<PhoneNumberDto> getPhoneNumber() {
        return phoneNumber;
    }

    public UserDto(Long id, String lastName, String firstName, Set<EmailDto> emails, Set<PhoneNumberDto> phoneNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emails = emails;
        this.phoneNumber = phoneNumber;
    }
}
