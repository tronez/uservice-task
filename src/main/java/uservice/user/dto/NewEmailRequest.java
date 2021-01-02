package uservice.user.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;

public class NewEmailRequest {

    @Email(message = "Wrong email format!")
    private String mail;
    @Range(message = "Wrong user id")
    private Long userId;

    public NewEmailRequest(String mail, Long userId) {
        this.mail = mail;
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public Long getUserId() {
        return userId;
    }
}