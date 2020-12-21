package uservice.user.domain;

import org.springframework.stereotype.Component;
import uservice.user.dto.PhoneNumberDto;

@Component
class PhoneNumberMapper {

    private UserRepository repository;

    PhoneNumberMapper(UserRepository repository) {
        this.repository = repository;
    }

    PhoneNumber toEntity(PhoneNumberDto dto) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber
                .setId(dto.getId())
                .setNumber(dto.getNumber());

        if (dto.getId() != null) {
            repository.findById(dto.getUserId())
                    .ifPresent(phoneNumber::setUser);
        }

        return phoneNumber;
    }
}
