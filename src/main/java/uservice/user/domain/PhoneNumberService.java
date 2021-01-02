package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.exception.DuplicateResourceException;
import uservice.exception.ResourceNotFoundException;
import uservice.user.dto.PhoneNumberDTO;

@Service
class PhoneNumberService {

    private PhoneNumberRepository repository;

    PhoneNumberService(PhoneNumberRepository repository) {
        this.repository = repository;
    }

    PhoneNumber savePhoneNumber(PhoneNumberDTO phoneNumberDTO, User user) {

        checkForDuplicatePhoneNumberAndThrow(phoneNumberDTO.getNumber());
        final PhoneNumber phoneNumber = PhoneNumber.createFromDTO(phoneNumberDTO, user);

        return repository.save(phoneNumber);
    }

    PhoneNumber updatePhoneNumber(Long numberId, User user, String newPhoneNumber) {

        checkForDuplicatePhoneNumberAndThrow(newPhoneNumber);
        PhoneNumber phoneNumber = findByIdOrThrow(numberId);

        if (user.containsPhoneNumber(phoneNumber)) {
            phoneNumber.setNumber(newPhoneNumber);
            return repository.save(phoneNumber);
        }

        throw new ResourceNotFoundException("Couldn't find phone number for given user");
    }

    PhoneNumber findByIdOrThrow(Long id) {

        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Phone number with given id was not found"));
    }

    private void checkForDuplicatePhoneNumberAndThrow(String number) {
        repository
                .findByNumber(number)
                .ifPresent(n -> {
                    throw new DuplicateResourceException("There is already a resource with given phone number: " + number);
                });
    }
}
