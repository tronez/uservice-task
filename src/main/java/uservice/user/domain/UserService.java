package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.exception.ResourceNotFoundException;
import uservice.user.dto.EmailDto;
import uservice.user.dto.PhoneNumberDto;
import uservice.user.dto.UserDto;

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

    UserDto save(UserDto dto) {

        final User userToSave = User.createFromDTO(dto);
        final User savedUser = repository.save(userToSave);
        return savedUser.toDTO();
    }

    EmailDto addEmailToUser(EmailDto emailDto) {

        final User user = findByIdOrThrow(emailDto.getUserId());
        final Email email = emailService.saveEmail(emailDto, user);
        return email.toDTO();
    }

    PhoneNumberDto addPhoneNumberToUser(PhoneNumberDto phoneNumberDto) {

        final User user = findByIdOrThrow(phoneNumberDto.getUserId());
        final PhoneNumber phoneNumber = phoneNumberService.savePhoneNumber(phoneNumberDto, user);
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
