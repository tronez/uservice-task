package uservice.user.domain;

import org.springframework.stereotype.Component;
import uservice.user.dto.UserDto;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class UserMapper {

    private EmailMapper emailMapper;
    private PhoneNumberMapper phoneNumberMapper;

    UserMapper(EmailMapper emailMapper, PhoneNumberMapper phoneNumberMapper) {
        this.emailMapper = emailMapper;
        this.phoneNumberMapper = phoneNumberMapper;
    }

    User toEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        final Set<Email> emails = dto.getEmails().stream()
                .map(emailMapper::toEntity)
                .collect(Collectors.toSet());

        final Set<PhoneNumber> phoneNumbers = dto.getPhoneNumber().stream()
                .map(phoneNumberMapper::toEntity)
                .collect(Collectors.toSet());

        entity.setEmails(emails);
        entity.setPhoneNumber(phoneNumbers);
        emails.forEach(email -> email.setUser(entity));
        phoneNumbers.forEach(phoneNumber -> phoneNumber.setUser(entity));

        return entity;
    }
}
