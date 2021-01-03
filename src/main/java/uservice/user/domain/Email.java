package uservice.user.domain;

import uservice.user.dto.EmailDTO;
import uservice.user.dto.EmailRequest;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    static Email createFromDTO(EmailDTO emailDTO, User user) {
        return new Email(emailDTO.getId(), emailDTO.getMail(), user);
    }

    static Email createFromRequest(EmailRequest emailRequest, User user) {
        return new Email(null, emailRequest.getEmail(), user);
    }

    Email(Long id, String mail, User user) {
        this.id = id;
        this.mail = mail;
        this.user = user;
    }

    Email() {
    }

    Long getId() {
        return id;
    }

    Email setId(Long id) {
        this.id = id;
        return this;
    }

    String getMail() {
        return mail;
    }

    User getUser() {
        return user;
    }

    Email setUser(User user) {
        this.user = user;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    EmailDTO toDTO() {
        return new EmailDTO(id, mail, user.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return id.equals(email.id) &&
                mail.equals(email.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail);
    }
}