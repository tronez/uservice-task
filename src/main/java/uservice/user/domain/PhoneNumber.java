package uservice.user.domain;

import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.PhoneNumberRequest;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    static PhoneNumber createFromDTO(PhoneNumberDTO phoneNumberDTO, User user) {
        return new PhoneNumber(phoneNumberDTO.getId(), phoneNumberDTO.getNumber(), user);
    }

    static PhoneNumber createFromRequest(PhoneNumberRequest phoneNumberRequest, User user) {
        return new PhoneNumber(null, phoneNumberRequest.getPhoneNumber(), user);
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

    PhoneNumberDTO toDTO() {
        return new PhoneNumberDTO(id, number, user.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return id.equals(that.id) &&
                number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }
}