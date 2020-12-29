package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.user.dto.EmailDTO;

@Service
class EmailService {

    private final EmailRepository repository;

    EmailService(EmailRepository repository) {
        this.repository = repository;
    }

    Email saveEmail(EmailDTO emailDTO, User user) {

        final Email email = Email.createFromDTO(emailDTO, user);

        return repository.save(email);
    }
}
