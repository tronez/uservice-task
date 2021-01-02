package uservice.user.dto;

import javax.validation.constraints.Email;

public class EmailRequest {

    @Email(message = "Wrong email format!")
    private String email;

    public EmailRequest(String email) {
        this.email = email;
    }

    public EmailRequest() {
    }

    public String getEmail() {
        return email;
    }
}
