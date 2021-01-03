package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.exception.ResourceNotFoundException;
import uservice.user.dto.EmailDTO;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserRequest;

@Service
class UserService {

    private UserRepository repository;
    private EmailService emailService;
    private PhoneNumberService phoneNumberService;

    UserService(UserRepository repository,
                EmailService emailService,
                PhoneNumberService phoneNumberService) {

        this.repository = repository;
        this.emailService = emailService;
        this.phoneNumberService = phoneNumberService;
    }

    User save(UserRequest userResponse) {


        final User userToSave = User.createFromDTO(userResponse);
        return repository.save(userToSave);
    }

    Email addEmailToUser(EmailDTO emailDTO) {

        final User user = findByIdOrThrow(emailDTO.getUserId());
        return emailService.saveEmail(emailDTO, user);
    }

    PhoneNumber addPhoneNumberToUser(PhoneNumberDTO phoneNumberDTO) {

        final User user = findByIdOrThrow(phoneNumberDTO.getUserId());
        return phoneNumberService.savePhoneNumber(phoneNumberDTO, user);
    }

    User findByLastName(String lastName) {

        return repository
                .findFirstByLastNameContaining(lastName)
                .orElseThrow(() -> new ResourceNotFoundException("User with given last name was not found"));
    }

    User findByIdOrThrow(Long id) {

        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id was not found"));
    }

    void delete(Long id) {

        repository.deleteById(id);
    }
}
