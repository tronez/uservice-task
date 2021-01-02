package uservice.user.dto;

import org.hibernate.validator.constraints.Length;

public class NewPhoneNumberRequest {

    @Length(min = 9, max = 9, message = "Phone number must consist of 9 digits")
    private String phoneNumber;
    private Long userId;

    public NewPhoneNumberRequest(String phoneNumber, Long userId) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }
}
