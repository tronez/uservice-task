package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.EmailDto;
import uservice.user.dto.NewEmailDTO;
import uservice.user.dto.NewPhoneNumberDTO;
import uservice.user.dto.PhoneNumberDto;
import uservice.user.dto.UserDto;

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

    public UserDto saveUser(UserDto userDto) {
        return userService.save(userDto);
    }

    public EmailDto addEmailToUser(NewEmailDTO newEmailDTO) {
        final EmailDto emailDto = EmailDto.fromNewEmailDTO(newEmailDTO);
        return userService.addEmailToUser(emailDto);
    }

    public PhoneNumberDto addPhoneNumberToUser(NewPhoneNumberDTO newPhoneNumberDTO){
        final PhoneNumberDto phoneNumberDto = PhoneNumberDto.fromNewPhoneNumberDTO(newPhoneNumberDTO);
        return userService.addPhoneNumberToUser(phoneNumberDto);
    }

    public UserDto findUserById(Long id){
        return userService
                .findByIdOrThrow(id)
                .toDTO();
    }

    public UserDto findUserByLastName(String lastName){
        return userService
                .findByLastName(lastName)
                .toDTO();
    }

    public void deleteUserById(Long id){
        userService.delete(id);
    }
}
