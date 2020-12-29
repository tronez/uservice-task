package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.exception.ResourceNotFoundException;
import uservice.user.dto.EmailDTO;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserDTO;

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

    UserDTO save(UserDTO userDTO) {

        final User userToSave = User.createFromDTO(userDTO);
        final User savedUser = repository.save(userToSave);
        return savedUser.toDTO();
    }

    EmailDTO addEmailToUser(EmailDTO emailDTO) {

        final User user = findByIdOrThrow(emailDTO.getUserId());
        final Email email = emailService.saveEmail(emailDTO, user);
        return email.toDTO();
    }

    PhoneNumberDTO addPhoneNumberToUser(PhoneNumberDTO phoneNumberDTO) {

        final User user = findByIdOrThrow(phoneNumberDTO.getUserId());
        final PhoneNumber phoneNumber = phoneNumberService.savePhoneNumber(phoneNumberDTO, user);
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
