package uservice.user.domain;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;
    private UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }
}
