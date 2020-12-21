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

    public Long getId() {
        return id;
    }

    public Email setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public Email setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Email setUser(User user) {
        this.user = user;
        return this;
    }

    EmailDto toDto() {
        return new EmailDto(id, mail, user.getId());
    }
}