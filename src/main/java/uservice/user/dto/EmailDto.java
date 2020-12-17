package uservice.user.dto;

import javax.validation.constraints.Email;

public class EmailDto {

    private Long id;
    @Email(message = "Wrong email format!")
    private String mail;
    private Long userId;

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public Long getUserId() {
        return userId;
    }

    public EmailDto(Long id, String mail, Long userId) {
        this.id = id;
        this.mail = mail;
        this.userId = userId;
    }

}
