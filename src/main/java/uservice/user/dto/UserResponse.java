package uservice.user.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class UserResponse {

    private Long id;
    @NotBlank(message = "User's first name can't be empty!")
    private String firstName;
    @NotBlank(message = "User's last name can't be empty!")
    private String lastName;
    @Valid
    @NotEmpty(message = "User has to have at least one email")
    private List<EmailDTO> emails;
    @Valid
    @NotEmpty(message = "User has to have at least one phone number")
    private List<PhoneNumberDTO> phoneNumber;

    public static UserResponse fromUserRequest(UserRequest dto) {
        return new UserResponse(null, dto.getFirstName(), dto.getLastName(), null, null);
    }

    public UserResponse(Long id, String firstName, String lastName, List<EmailDTO> emails,
                        List<PhoneNumberDTO> phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emails = emails;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<EmailDTO> getEmails() {
        return emails;
    }

    public List<PhoneNumberDTO> getPhoneNumber() {
        return phoneNumber;
    }
}
