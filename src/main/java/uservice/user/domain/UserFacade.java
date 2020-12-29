package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.EmailDTO;
import uservice.user.dto.NewEmailDTO;
import uservice.user.dto.NewPhoneNumberDTO;
import uservice.user.dto.NewUserDTO;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserDTO;

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

    public UserDTO saveUser(NewUserDTO newUserDTO) {
        final UserDTO userDTO = UserDTO.fromNewUserDTO(newUserDTO);
        return userService.save(userDTO);
    }

    public EmailDTO addEmailToUser(NewEmailDTO newEmailDTO) {
        final EmailDTO emailDto = EmailDTO.fromNewEmailDTO(newEmailDTO);
        return userService.addEmailToUser(emailDto);
    }

    public PhoneNumberDTO addPhoneNumberToUser(NewPhoneNumberDTO newPhoneNumberDTO){
        final PhoneNumberDTO phoneNumberDto = PhoneNumberDTO.fromNewPhoneNumberDTO(newPhoneNumberDTO);
        return userService.addPhoneNumberToUser(phoneNumberDto);
    }

    public UserDTO findUserById(Long id){
        return userService
                .findByIdOrThrow(id)
                .toDTO();
    }

    public UserDTO findUserByLastName(String lastName){
        return userService
                .findByLastName(lastName)
                .toDTO();
    }

    public void deleteUserById(Long id){
        userService.delete(id);
    }
}
