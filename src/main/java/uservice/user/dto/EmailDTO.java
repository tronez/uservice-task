package uservice.user.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;

public class EmailDTO {

    @Range(min = 1L, message = "email id out of range")
    private Long id;
    @Email(message = "Wrong email format!")
    private String email;
    @Range(min = 1L, message = "email id out of range")
    private Long userId;

    public static EmailDTO fromNewEmailRequest(NewEmailRequest newEmailRequest) {
        return new EmailDTO(newEmailRequest.getEmail(), newEmailRequest.getUserId());
    }

    public EmailDTO(Long id, String email, Long userId) {
        this.id = id;
        this.email = email;
        this.userId = userId;
    }

    public EmailDTO(String email, Long userId) {
        this.email = email;
        this.userId = userId;
    }

    public EmailDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Long getUserId() {
        return userId;
    }
}
