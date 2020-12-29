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

    static User createFromDTO(UserDto dto) {

        User entity = new User();
        final Set<Email> emails = dto.getEmails().stream()
                .map(emailDto -> Email.createFromDTO(emailDto, entity))
                .collect(Collectors.toSet());
        final Set<PhoneNumber> phoneNumbers = dto.getPhoneNumber().stream()
                .map(phoneNumberDto -> PhoneNumber.createFromDTO(phoneNumberDto, entity))
                .collect(Collectors.toSet());

        entity
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setEmails(emails)
                .setPhoneNumber(phoneNumbers);

        return entity;
    }

    Long getId() {
        return id;
    }

    User setId(Long id) {
        this.id = id;
        return this;
    }

    String getLastName() {
        return lastName;
    }

    User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    String getFirstName() {
        return firstName;
    }

    User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    Set<Email> getEmails() {
        return emails;
    }

    User setEmails(Set<Email> emails) {
        this.emails = emails;
        return this;
    }

    Set<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }

    User setPhoneNumber(Set<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    UserDto toDTO() {
        Set<EmailDto> emailDTOS = emails.stream()
                .map(Email::toDTO)
                .collect(Collectors.toSet());

        Set<PhoneNumberDto> phoneNumberDTOS = phoneNumber.stream()
                .map(PhoneNumber::toDTO)
                .collect(Collectors.toSet());

        return new UserDto(id, firstName, lastName, emailDTOS, phoneNumberDTOS);
    }
}