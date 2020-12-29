package uservice.user.dto;

import javax.validation.constraints.Email;

public class EmailDTO {

    private Long id;
    @Email(message = "Wrong email format!")
    private String mail;
    private Long userId;

    public static EmailDTO fromNewEmailDTO(NewEmailDTO newEmailDTO) {
        return new EmailDTO(null, newEmailDTO.getMail(), newEmailDTO.getUserId());
    }

    public EmailDTO(Long id, String mail, Long userId) {
        this.id = id;
        this.mail = mail;
        this.userId = userId;
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
