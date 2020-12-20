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

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getMail() {
        return mail;
    }

    void setMail(String mail) {
        this.mail = mail;
    }

    User getUser() {
        return user;
    }

    void setUser(User user) {
        this.user = user;
    }

    EmailDto toDto() {
        return new EmailDto(id, mail, user.getId());
    }
}