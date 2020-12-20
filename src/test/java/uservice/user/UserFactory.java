package uservice.user;

import uservice.user.dto.EmailDto;
import uservice.user.dto.PhoneNumberDto;
import uservice.user.dto.UserDto;

import java.util.HashSet;
import java.util.Set;

class UserFactory {

    static UserDto createUserForSaving() {
        final Set<EmailDto> emailDtos = new HashSet<>();
        emailDtos.add(new EmailDto(null, "jdoe@gmail.com", null));

        final Set<PhoneNumberDto> phoneNumberDtos = new HashSet<>();
        phoneNumberDtos.add(new PhoneNumberDto(null, "111111111", null));

        return new UserDto(null, "Jon", "Doe", emailDtos, phoneNumberDtos);
    }
}
