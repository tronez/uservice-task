package uservice.user.domain;

import uservice.user.dto.EmailDTO;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserDTO;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
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

    static User createFromDTO(UserDTO userDTO) {

        User entity = new User();
        final Set<Email> emails = userDTO.getEmails().stream()
                .map(emailDTO -> Email.createFromDTO(emailDTO, entity))
                .collect(Collectors.toSet());
        final Set<PhoneNumber> phoneNumbers = userDTO.getPhoneNumber().stream()
                .map(phoneNumberDTO -> PhoneNumber.createFromDTO(phoneNumberDTO, entity))
                .collect(Collectors.toSet());

        entity
                .setId(userDTO.getId())
                .setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
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

    UserDTO toDTO() {
        List<EmailDTO> emailDTOS = emails.stream()
                .map(Email::toDTO)
                .collect(Collectors.toList());

        List<PhoneNumberDTO> phoneNumberDTOS = phoneNumber.stream()
                .map(PhoneNumber::toDTO)
                .collect(Collectors.toList());

        return new UserDTO(id, firstName, lastName, emailDTOS, phoneNumberDTOS);
    }
}