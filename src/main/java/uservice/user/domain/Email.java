package uservice.user.domain;

import uservice.user.dto.EmailDto;

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

    static Email createFromDTO(EmailDto emailDto, User user) {
        return new Email(emailDto.getId(), emailDto.getMail(), user);
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

    EmailDto toDTO() {
        return new EmailDto(id, mail, user.getId());
    }
}