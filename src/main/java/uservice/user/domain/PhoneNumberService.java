package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.PhoneNumberDto;

@Service
class PhoneNumberService {

    private PhoneNumberRepository repository;

    PhoneNumberService(PhoneNumberRepository repository) {
        this.repository = repository;
    }

    PhoneNumber savePhoneNumber(PhoneNumberDto phoneNumberDto, User user) {

        final PhoneNumber phoneNumber = PhoneNumber.createFromDTO(phoneNumberDto, user);

        return repository.save(phoneNumber);
    }
}
