package uservice.user;

import uservice.user.dto.EmailDTO;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

class UserFactory {

    static UserDTO createUserForSaving() {
        final List<EmailDTO> emailDTO = new ArrayList<>();
        emailDTO.add(new EmailDTO(null, "jdoe@gmail.com", null));

        final List<PhoneNumberDTO> phoneNumberDTO = new ArrayList<>();
        phoneNumberDTO.add(new PhoneNumberDTO(null, "111111111", null));

        return new UserDTO(null, "Jon", "Doe", emailDTO, phoneNumberDTO);
    }
}
