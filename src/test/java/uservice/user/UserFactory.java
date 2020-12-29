package uservice.user;

import uservice.user.dto.EmailDTO;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserDTO;

import java.util.HashSet;
import java.util.Set;

class UserFactory {

    static UserDTO createUserForSaving() {
        final Set<EmailDTO> emailDTO = new HashSet<>();
        emailDTO.add(new EmailDTO(null, "jdoe@gmail.com", null));

        final Set<PhoneNumberDTO> phoneNumberDTO = new HashSet<>();
        phoneNumberDTO.add(new PhoneNumberDTO(null, "111111111", null));

        return new UserDTO(null, "Jon", "Doe", emailDTO, phoneNumberDTO);
    }
}
