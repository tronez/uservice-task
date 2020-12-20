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

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getNumber() {
        return number;
    }

    void setNumber(String number) {
        this.number = number;
    }

    User getUser() {
        return user;
    }

    void setUser(User user) {
        this.user = user;
    }

    PhoneNumberDto toDto() {
        return new PhoneNumberDto(id, number, user.getId());
    }
}