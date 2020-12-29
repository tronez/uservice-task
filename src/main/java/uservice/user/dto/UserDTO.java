package uservice.user.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {

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

    public static UserDTO fromNewUserDTO(NewUserDTO dto) {
        final List<EmailDTO> emailDTOS = dto.getEmails().stream()
                .map(EmailDTO::fromNewEmailDTO)
                .collect(Collectors.toList());
        final List<PhoneNumberDTO> phoneNumberDTOS = dto.getPhoneNumber().stream()
                .map(PhoneNumberDTO::fromNewPhoneNumberDTO)
                .collect(Collectors.toList());

        return new UserDTO(null, dto.getFirstName(), dto.getLastName(), emailDTOS, phoneNumberDTOS);
    }

    public UserDTO(Long id, String firstName, String lastName, List<EmailDTO> emails,
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
