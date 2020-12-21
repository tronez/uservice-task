package uservice.user.domain;

import uservice.user.dto.PhoneNumberDto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public PhoneNumber setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public PhoneNumber setNumber(String number) {
        this.number = number;
        return this;
    }

    public User getUser() {
        return user;
    }

    public PhoneNumber setUser(User user) {
        this.user = user;
        return this;
    }

    PhoneNumberDto toDto() {
        return new PhoneNumberDto(id, number, user.getId());
    }
}