package uservice.user.domain;

import uservice.user.dto.EmailDto;
import uservice.user.dto.PhoneNumberDto;
import uservice.user.dto.UserDto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Email> emails;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PhoneNumber> phoneNumber;

    Long getId() {
        return id;
    }

    String getLastName() {
        return lastName;
    }

    String getFirstName() {
        return firstName;
    }

    Set<Email> getEmails() {
        return emails;
    }

    Set<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }

    void setId(Long id) {
        this.id = id;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    void setPhoneNumber(Set<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    UserDto toDto() {
        final Set<EmailDto> emailDtos = emails.stream()
                .map(Email::toDto)
                .collect(Collectors.toSet());

        final Set<PhoneNumberDto> phoneNumberDtos = phoneNumber.stream()
                .map(PhoneNumber::toDto)
                .collect(Collectors.toSet());

        return new UserDto(id, firstName, lastName, emailDtos, phoneNumberDtos);
    }
}