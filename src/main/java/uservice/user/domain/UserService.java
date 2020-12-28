package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.exception.ResourceNotFoundException;
import uservice.user.dto.EmailDto;
import uservice.user.dto.NewEmailDTO;
import uservice.user.dto.NewPhoneNumberDTO;
import uservice.user.dto.PhoneNumberDto;
import uservice.user.dto.UserDto;

@Service
public class UserService {

    private UserRepository repository;
    private UserMapper userMapper;
    private EmailService emailService;
    private PhoneNumberService phoneNumberService;

    public UserService(UserRepository repository,
                       UserMapper userMapper,
                       EmailService emailService,
                       PhoneNumberService phoneNumberService) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.phoneNumberService = phoneNumberService;
    }

    public UserDto save(UserDto dto) {
        final User userToSave = userMapper.toEntity(dto);
        final User savedUser = repository.save(userToSave);
        return savedUser.toDto();
    }

    public EmailDto addEmailToUser(NewEmailDTO newEmailDTO) {
        final User user = findByIdOrThrow(newEmailDTO.getUserId());
        final EmailDto emailToSave = EmailDto.fromNewEmailDTO(newEmailDTO);
        final Email email = emailService.saveEmail(emailToSave, user);
        return email.toDTO();
    }

    public PhoneNumberDto addPhoneNumberToUser(NewPhoneNumberDTO newPhoneNumberDTO) {
        final User user = findByIdOrThrow(newPhoneNumberDTO.getUserId());
        final PhoneNumberDto phoneNumberToSave = PhoneNumberDto.fromNewPhoneNumberDTO(newPhoneNumberDTO);
        final PhoneNumber phoneNumber = phoneNumberService.savePhoneNumber(phoneNumberToSave, user);
        return phoneNumber.toDTO();
    }

    public UserDto getUserById(Long id) {
        return findByIdOrThrow(id).toDto();
    }

    public UserDto findByLastName(String lastName) {
        return repository
                .findAllByLastNameContaining(lastName)
                .map(User::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with given last name was not found"));
    }

    public void delete(Long id) {
        repository
                .findById(id)
                .ifPresent(repository::delete);
    }

    private User findByIdOrThrow(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id was not found"));
    }
}
