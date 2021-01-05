package uservice.user;

import uservice.user.dto.NewPhoneNumberRequest;
import uservice.user.dto.PhoneNumberDTO;

class PhoneNumberFactory {

    static NewPhoneNumberRequest createPhoneNumberForSaving() {
        return new NewPhoneNumberRequest("666456789", 1L);
    }

    static PhoneNumberDTO createPhoneNumberForUpdate() {
        return new PhoneNumberDTO(1L, "123456666", 1L);
    }
}
