package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.EmailDto;

@Service
public class EmailService {

    private EmailRepository repository;
    private EmailMapper mapper;

    public EmailService(EmailRepository repository, EmailMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Email saveEmail(EmailDto emailDto, User user) {
        final Email email = mapper.toEntity(emailDto);
        email.setUser(user);
        return repository.save(email);
    }
}
