package uservice.user.dto;

import javax.validation.constraints.Email;

public class EmailResponse {

    private Long id;
    @Email(message = "Wrong email format!")
    private String mail;
    private Long userId;

    public static EmailResponse fromNewEmailRequest(NewEmailRequest newEmailRequest) {
        return new EmailResponse(newEmailRequest.getMail(), newEmailRequest.getUserId());
    }

    public static EmailResponse fromEmailRequest(EmailRequest emailRequest) {
        return new EmailResponse(emailRequest.getEmail());
    }

    public EmailResponse(Long id, String mail, Long userId) {
        this.id = id;
        this.mail = mail;
        this.userId = userId;
    }

    public EmailResponse(String mail, Long userId) {
        this.mail = mail;
        this.userId = userId;
    }

    public EmailResponse(String mail) {
        this.mail = mail;
    }

    public EmailResponse() {
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
