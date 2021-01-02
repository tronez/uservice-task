package uservice.user;

import uservice.user.dto.EmailDTO;
import uservice.user.dto.EmailRequest;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.PhoneNumberRequest;
import uservice.user.dto.UserRequest;
import uservice.user.dto.UserResponse;

import java.util.ArrayList;
import java.util.List;

class UserFactory {

    static UserRequest createUserForSaving() {
        final List<EmailRequest> emailDTO = new ArrayList<>();
        emailDTO.add(new EmailRequest("jdoe@gmail.com"));

        final List<PhoneNumberRequest> phoneNumberDTO = new ArrayList<>();
        phoneNumberDTO.add(new PhoneNumberRequest("123456789"));

        return new UserRequest("Jon", "Doe", emailDTO, phoneNumberDTO);
    }
}
