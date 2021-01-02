package uservice.user.domain;

import org.springframework.stereotype.Service;
import uservice.exception.DuplicateResourceException;
import uservice.exception.ResourceNotFoundException;
import uservice.user.dto.EmailDTO;

@Service
class EmailService {

    private final EmailRepository repository;

    EmailService(EmailRepository repository) {
        this.repository = repository;
    }

    Email saveEmail(EmailDTO emailDTO, User user) {

        checkForDuplicateEmailAndThrow(emailDTO.getMail());
        final Email email = Email.createFromDTO(emailDTO, user);

        return repository.save(email);
    }

    Email findByIdOrThrow(Long emailID) {

        return repository
                .findById(emailID)
                .orElseThrow(() -> new ResourceNotFoundException("Email with given id was not found"));
    }

    Email updateEmail(Long emailId, User user, String newEmail) {

        checkForDuplicateEmailAndThrow(newEmail);
        Email email = findByIdOrThrow(emailId);

        if (user.containsEmail(email)) {
            email.setMail(newEmail);
            return repository.save(email);
        }

        throw new ResourceNotFoundException("Couldn't find email for given user");
    }

    private void checkForDuplicateEmailAndThrow(String email) {
        repository
                .findByMail(email)
                .ifPresent(m -> {
                    throw new DuplicateResourceException("There is already a resource with given email: " + email);
                });
    }
}
