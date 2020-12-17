package uservice.user.dto;

import javax.validation.constraints.Size;

public class PhoneNumberDto {

    private Long id;
    private String number;
    private Long userId;

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Long getUserId() {
        return userId;
    }

    public PhoneNumberDto(Long id, String number, Long userId) {
        this.id = id;
        this.number = number;
        this.userId = userId;
    }
}
