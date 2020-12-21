package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.exception.ResourceNotFoundException;
import uservice.user.dto.UserDto;

@Service
public class UserService {

    private UserRepository repository;
    private UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public UserDto save(UserDto dto) {
        final User userToSave = userMapper.toEntity(dto);
        final User savedUser = repository.save(userToSave);
        return savedUser.toDto();
    }

    public UserDto findById(Long id) {
        return repository.findById(id)
                .map(User::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id was not found"));
    }

    public UserDto findByLastName(String lastName) {
        return repository.findAllByLastNameContaining(lastName)
                .map(User::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with given last name was not found"));
    }

    public void delete(Long id) {
        repository.findById(id)
                .ifPresent(repository::delete);
    }
}
