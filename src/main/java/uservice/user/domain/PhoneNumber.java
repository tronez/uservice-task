package uservice.user.domain;

import uservice.user.dto.PhoneNumberResponse;

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

    static PhoneNumber createFromDTO(PhoneNumberResponse phoneNumberResponse, User user) {
        return new PhoneNumber(phoneNumberResponse.getId(), phoneNumberResponse.getNumber(), user);
    }

    PhoneNumber() {
    }

    PhoneNumber(Long id, String number, User user) {
        this.id = id;
        this.number = number;
        this.user = user;
    }

    Long getId() {
        return id;
    }

    PhoneNumber setId(Long id) {
        this.id = id;
        return this;
    }

    String getNumber() {
        return number;
    }

    PhoneNumber setNumber(String number) {
        this.number = number;
        return this;
    }

    User getUser() {
        return user;
    }

    PhoneNumber setUser(User user) {
        this.user = user;
        return this;
    }

    PhoneNumberResponse toDTO() {
        return new PhoneNumberResponse(id, number, user.getId());
    }
}