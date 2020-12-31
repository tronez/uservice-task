package uservice.user.dto;

import javax.validation.constraints.Email;

public class EmailRequest {

    @Email(message = "Wrong email format!")
    private String email;

    public String getEmail() {
        return email;
    }
}
