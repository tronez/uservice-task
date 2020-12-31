package uservice.user.dto;

public class PhoneNumberResponse {

    private Long id;
    private String number;
    private Long userId;

    public static PhoneNumberResponse fromNewPhoneNumberRequest(NewPhoneNumberRequest newPhoneNumberRequest) {
        return new PhoneNumberResponse(newPhoneNumberRequest.getNumber(), newPhoneNumberRequest.getUserId());
    }

    public static PhoneNumberResponse fromPhoneNumberRequest(PhoneNumberRequest phoneNumberRequest) {
        return new PhoneNumberResponse(phoneNumberRequest.getPhoneNumber());
    }

    public PhoneNumberResponse(Long id, String number, Long userId) {
        this.id = id;
        this.number = number;
        this.userId = userId;
    }

    public PhoneNumberResponse(String number, Long userId) {
        this.number = number;
        this.userId = userId;
    }

    public PhoneNumberResponse(String number) {
        this.number = number;
    }

    public PhoneNumberResponse() {
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
