package uservice.user.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;

public class NewEmailRequest {

    @Email(message = "Wrong email format!")
    private String email;
    @Range(message = "Wrong user id")
    private Long userId;

    public NewEmailRequest(String email, Long userId) {
        this.email = email;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public Long getUserId() {
        return userId;
    }
}
