package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.PhoneNumberDTO;

@Service
class PhoneNumberService {

    private PhoneNumberRepository repository;

    PhoneNumberService(PhoneNumberRepository repository) {
        this.repository = repository;
    }

    PhoneNumber savePhoneNumber(PhoneNumberDTO phoneNumberDTO, User user) {

        final PhoneNumber phoneNumber = PhoneNumber.createFromDTO(phoneNumberDTO, user);

        return repository.save(phoneNumber);
    }
}
