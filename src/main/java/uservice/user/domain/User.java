package uservice.user.domain;

import uservice.user.dto.EmailDTO;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserResponse;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
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

    static User createFromDTO(UserResponse userResponse) {

        User entity = new User();
        final Set<Email> emails = userResponse.getEmails().stream()
                .map(emailDTO -> Email.createFromDTO(emailDTO, entity))
                .collect(Collectors.toSet());
        final Set<PhoneNumber> phoneNumbers = userResponse.getPhoneNumber().stream()
                .map(phoneNumberDTO -> PhoneNumber.createFromDTO(phoneNumberDTO, entity))
                .collect(Collectors.toSet());

        entity
                .setId(userResponse.getId())
                .setFirstName(userResponse.getFirstName())
                .setLastName(userResponse.getLastName())
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

    boolean containsEmail(Email email) {
        return getEmails().contains(email);
    }

    boolean containsPhoneNumber(PhoneNumber phoneNumber) {
        return getPhoneNumbers().contains(phoneNumber);
    }

    Set<Email> getEmails() {
        return emails;
    }

    User setEmails(Set<Email> emails) {
        this.emails = emails;
        return this;
    }

    Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumber;
    }

    User setPhoneNumber(Set<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    UserResponse toDTO() {
        List<EmailDTO> emailResponses = emails.stream()
                .map(Email::toDTO)
                .collect(Collectors.toList());

        List<PhoneNumberDTO> phoneNumberResponses = phoneNumber.stream()
                .map(PhoneNumber::toDTO)
                .collect(Collectors.toList());

        return new UserResponse(id, firstName, lastName, emailResponses, phoneNumberResponses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(emails, user.emails) &&
                Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, emails, phoneNumber);
    }
}