package uservice.user.domain;

import org.springframework.stereotype.Component;
import uservice.user.dto.EmailDto;

@Component
class EmailMapper {

    private UserRepository repository;

    EmailMapper(UserRepository repository) {
        this.repository = repository;
    }

    Email toEntity(EmailDto dto) {
        Email email = new Email();
        email
                .setId(dto.getId())
                .setMail(dto.getMail());

        if (dto.getId() != null) {
            repository.findById(dto.getUserId())
                    .ifPresent(email::setUser);
        }
        return email;
    }
}
