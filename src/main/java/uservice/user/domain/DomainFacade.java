package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.EmailDTO;
import uservice.user.dto.NewEmailRequest;
import uservice.user.dto.NewPhoneNumberRequest;
import uservice.user.dto.UserRequest;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserResponse;

@Service
public class DomainFacade {

    private final UserService userService;
    private final EmailService emailService;
    private final PhoneNumberService phoneNumberService;

    public DomainFacade(UserService userService, EmailService emailService, PhoneNumberService phoneNumberService) {
        this.userService = userService;
        this.emailService = emailService;
        this.phoneNumberService = phoneNumberService;
    }

    public UserResponse saveUser(UserRequest userRequest) {

        final UserResponse userResponse = UserResponse.fromNewUserDTO(userRequest);
        return userService
                .save(userResponse)
                .toDTO();
    }

    public EmailDTO addEmailToUser(NewEmailRequest newEmailRequest) {
        final EmailDTO emailDTO = EmailDTO.fromNewEmailRequest(newEmailRequest);

        return userService
                .addEmailToUser(emailDTO)
                .toDTO();
    }

    public EmailDTO updateUserEmail(Long userId, Long emailId, String newEmail) {

        final User user = userService.findByIdOrThrow(userId);
        return emailService
                .updateEmail(emailId, user, newEmail)
                .toDTO();
    }

    public PhoneNumberDTO addPhoneNumberToUser(NewPhoneNumberRequest newPhoneNumberRequest) {

        final PhoneNumberDTO phoneNumberDTO = PhoneNumberDTO.fromNewPhoneNumberRequest(newPhoneNumberRequest);
        return userService
                .addPhoneNumberToUser(phoneNumberDTO)
                .toDTO();
    }

    public PhoneNumberDTO updateUserPhoneNumber(Long userId, Long numberId, String newPhoneNumber) {

        final User user = userService.findByIdOrThrow(userId);
        return phoneNumberService
                .updatePhoneNumber(numberId, user, newPhoneNumber)
                .toDTO();
    }

    public UserResponse findUserById(Long id) {

        return userService
                .findByIdOrThrow(id)
                .toDTO();
    }

    public UserResponse findUserByLastName(String lastName) {

        return userService
                .findByLastName(lastName)
                .toDTO();
    }

    public void deleteUserById(Long id) {
        userService.delete(id);
    }
}
