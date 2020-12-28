package uservice.user.dto;

public class NewPhoneNumberDTO {

    private String number;
    private Long userId;

    public NewPhoneNumberDTO(String number, Long userId) {
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
