package uservice.user.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class PhoneNumberDTO {

    @Range(min = 1L, message = "Phone number id out of range")
    private Long id;
    @Length(min = 9, max = 9, message = "Phone number must consist of 9 digits")
    private String phoneNumber;
    @Range(min = 1L, message = "User id out of range")
    private Long userId;

    public static PhoneNumberDTO fromNewPhoneNumberRequest(NewPhoneNumberRequest newPhoneNumberRequest) {
        return new PhoneNumberDTO(newPhoneNumberRequest.getPhoneNumber(), newPhoneNumberRequest.getUserId());
    }

    public PhoneNumberDTO(Long id, String phoneNumber, Long userId) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public PhoneNumberDTO(String phoneNumber, Long userId) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public PhoneNumberDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getUserId() {
        return userId;
    }
}
