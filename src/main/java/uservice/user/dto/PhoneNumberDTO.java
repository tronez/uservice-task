package uservice.user.dto;

public class PhoneNumberDTO {

    private Long id;
    private String number;
    private Long userId;

    public static PhoneNumberDTO fromNewPhoneNumberDTO(NewPhoneNumberDTO newPhoneNumberDTO) {
        return new PhoneNumberDTO(null, newPhoneNumberDTO.getNumber(), newPhoneNumberDTO.getUserId());
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

    public PhoneNumberDTO(Long id, String number, Long userId) {
        this.id = id;
        this.number = number;
        this.userId = userId;
    }
}
