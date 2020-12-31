package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.EmailResponse;
import uservice.user.dto.NewEmailRequest;
import uservice.user.dto.NewPhoneNumberRequest;
import uservice.user.dto.UserRequest;
import uservice.user.dto.PhoneNumberResponse;
import uservice.user.dto.UserResponse;

@Service
public class UserFacade {

    private UserService userService;
    private EmailService emailService;
    private PhoneNumberService phoneNumberService;

    public UserFacade(UserService userService, EmailService emailService, PhoneNumberService phoneNumberService) {
        this.userService = userService;
        this.emailService = emailService;
        this.phoneNumberService = phoneNumberService;
    }

    public UserResponse saveUser(UserRequest userRequest) {
        final UserResponse userResponse = UserResponse.fromNewUserDTO(userRequest);
        return userService.save(userResponse);
    }

    public EmailResponse addEmailToUser(NewEmailRequest newEmailRequest) {
        final EmailResponse emailResponse = EmailResponse.fromNewEmailRequest(newEmailRequest);
        return userService.addEmailToUser(emailResponse);
    }

    public PhoneNumberResponse addPhoneNumberToUser(NewPhoneNumberRequest newPhoneNumberRequest){
        final PhoneNumberResponse phoneNumberResponse = PhoneNumberResponse.fromNewPhoneNumberRequest(newPhoneNumberRequest);
        return userService.addPhoneNumberToUser(phoneNumberResponse);
    }

    public UserResponse findUserById(Long id){
        return userService
                .findByIdOrThrow(id)
                .toDTO();
    }

    public UserResponse findUserByLastName(String lastName){
        return userService
                .findByLastName(lastName)
                .toDTO();
    }

    public void deleteUserById(Long id){
        userService.delete(id);
    }
}
