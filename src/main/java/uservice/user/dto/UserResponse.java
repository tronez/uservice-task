package uservice.user.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {

    private Long id;
    @NotBlank(message = "User's first name can't be empty!")
    private String firstName;
    @NotBlank(message = "User's last name can't be empty!")
    private String lastName;
    @Valid
    @NotEmpty(message = "User has to have at least one email")
    private List<EmailResponse> emails;
    @Valid
    @NotEmpty(message = "User has to have at least one phone number")
    private List<PhoneNumberResponse> phoneNumber;

    public static UserResponse fromNewUserDTO(UserRequest dto) {
        final List<EmailResponse> emailResponses = dto.getEmails().stream()
                .map(EmailResponse::fromEmailRequest)
                .collect(Collectors.toList());
        final List<PhoneNumberResponse> phoneNumberResponses = dto.getPhoneNumbers().stream()
                .map(PhoneNumberResponse::fromPhoneNumberRequest)
                .collect(Collectors.toList());

        return new UserResponse(null, dto.getFirstName(), dto.getLastName(), emailResponses, phoneNumberResponses);
    }

    public UserResponse(Long id, String firstName, String lastName, List<EmailResponse> emails,
                        List<PhoneNumberResponse> phoneNumber) {
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

    public List<EmailResponse> getEmails() {
        return emails;
    }

    public List<PhoneNumberResponse> getPhoneNumber() {
        return phoneNumber;
    }
}
