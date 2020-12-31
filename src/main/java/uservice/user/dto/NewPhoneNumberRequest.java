package uservice.user.dto;

public class NewPhoneNumberRequest {

    private String number;
    private Long userId;

    public NewPhoneNumberRequest(String number, Long userId) {
        this.number = number;
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public Long getUserId() {
        return userId;
    }
}
