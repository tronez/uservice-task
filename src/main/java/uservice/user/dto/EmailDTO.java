package uservice.user.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmailDTO {

    @NotNull(message = "email id cannot be empty")
    @Range(message = "email id out of range")
    private Long id;
    @Email(message = "Wrong email format!")
    private String mail;
    private Long userId;

    public static EmailDTO fromNewEmailRequest(NewEmailRequest newEmailRequest) {
        return new EmailDTO(newEmailRequest.getMail(), newEmailRequest.getUserId());
    }

    public static EmailDTO fromEmailRequest(EmailRequest emailRequest) {
        return new EmailDTO(emailRequest.getEmail());
    }

    public EmailDTO(Long id, String mail, Long userId) {
        this.id = id;
        this.mail = mail;
        this.userId = userId;
    }

    public EmailDTO(String mail, Long userId) {
        this.mail = mail;
        this.userId = userId;
    }

    public EmailDTO(String mail) {
        this.mail = mail;
    }

    public EmailDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public Long getUserId() {
        return userId;
    }
}
