package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.EmailDto;

@Service
class EmailService {

    private final EmailRepository repository;

    EmailService(EmailRepository repository) {
        this.repository = repository;
    }

    Email saveEmail(EmailDto emailDto, User user) {

        final Email email = Email.createFromDTO(emailDto, user);

        return repository.save(email);
    }
}
