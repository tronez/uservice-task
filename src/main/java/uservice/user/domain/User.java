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

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public User setEmails(Set<Email> emails) {
        this.emails = emails;
        return this;
    }

    public Set<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(Set<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
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