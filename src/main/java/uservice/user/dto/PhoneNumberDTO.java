package uservice.user.dto;

import org.hibernate.validator.constraints.Length;

public class PhoneNumberDTO {

    private Long id;
    @Length(min = 9, max = 9, message = "Phone number must consist of 9 digits")
    private String number;
    private Long userId;

    public static PhoneNumberDTO fromNewPhoneNumberRequest(NewPhoneNumberRequest newPhoneNumberRequest) {
        return new PhoneNumberDTO(newPhoneNumberRequest.getPhoneNumber(), newPhoneNumberRequest.getUserId());
    }

    public static PhoneNumberDTO fromPhoneNumberRequest(PhoneNumberRequest phoneNumberRequest) {
        return new PhoneNumberDTO(phoneNumberRequest.getPhoneNumber());
    }

    public PhoneNumberDTO(Long id, String number, Long userId) {
        this.id = id;
        this.number = number;
        this.userId = userId;
    }

    public PhoneNumberDTO(String number, Long userId) {
        this.number = number;
        this.userId = userId;
    }

    public PhoneNumberDTO(String number) {
        this.number = number;
    }

    public PhoneNumberDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Long getUserId() {
        return userId;
    }
}
