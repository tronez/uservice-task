package uservice.user;

import uservice.user.dto.NewPhoneNumberDTO;

class PhoneNumberFactory {

    static NewPhoneNumberDTO createPhoneNumberForSaving() {
        return new NewPhoneNumberDTO("123456789", 1L);
    }
}
