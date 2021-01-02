package uservice.user.dto;

import org.hibernate.validator.constraints.Length;

public class PhoneNumberRequest {

    @Length(min = 9, max = 9, message = "Phone number must consist of 9 digits")
    private String phoneNumber;

    public PhoneNumberRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumberRequest() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
