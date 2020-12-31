package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.PhoneNumberResponse;

@Service
class PhoneNumberService {

    private PhoneNumberRepository repository;

    PhoneNumberService(PhoneNumberRepository repository) {
        this.repository = repository;
    }

    PhoneNumber savePhoneNumber(PhoneNumberResponse phoneNumberResponse, User user) {

        final PhoneNumber phoneNumber = PhoneNumber.createFromDTO(phoneNumberResponse, user);

        return repository.save(phoneNumber);
    }
}
