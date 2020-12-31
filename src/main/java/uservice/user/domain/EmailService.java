package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.EmailResponse;

@Service
class EmailService {

    private final EmailRepository repository;

    EmailService(EmailRepository repository) {
        this.repository = repository;
    }

    Email saveEmail(EmailResponse emailResponse, User user) {

        final Email email = Email.createFromDTO(emailResponse, user);

        return repository.save(email);
    }
}
