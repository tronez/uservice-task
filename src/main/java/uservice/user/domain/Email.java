package uservice.user.domain;

import uservice.user.dto.EmailDTO;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    Email() {
    }

    Email(Long id, String mail, User user) {
        this.id = id;
        this.mail = mail;
        this.user = user;
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

    EmailDTO toDTO() {
        return new EmailDTO(id, mail, user.getId());
    }
}