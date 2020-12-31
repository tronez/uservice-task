package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.exception.ResourceNotFoundException;
import uservice.user.dto.EmailResponse;
import uservice.user.dto.PhoneNumberResponse;
import uservice.user.dto.UserResponse;

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

    UserResponse save(UserResponse userResponse) {

        final User userToSave = User.createFromDTO(userResponse);
        final User savedUser = repository.save(userToSave);
        return savedUser.toDTO();
    }

    EmailResponse addEmailToUser(EmailResponse emailResponse) {

        final User user = findByIdOrThrow(emailResponse.getUserId());
        final Email email = emailService.saveEmail(emailResponse, user);
        return email.toDTO();
    }

    PhoneNumberResponse addPhoneNumberToUser(PhoneNumberResponse phoneNumberResponse) {

        final User user = findByIdOrThrow(phoneNumberResponse.getUserId());
        final PhoneNumber phoneNumber = phoneNumberService.savePhoneNumber(phoneNumberResponse, user);
        return phoneNumber.toDTO();
    }

    User findByLastName(String lastName) {

        return repository
                .findAllByLastNameContaining(lastName)
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
