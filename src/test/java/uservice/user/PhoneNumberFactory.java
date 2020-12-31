package uservice.user;

import uservice.user.dto.NewPhoneNumberRequest;

class PhoneNumberFactory {

    static NewPhoneNumberRequest createPhoneNumberForSaving() {
        return new NewPhoneNumberRequest("123456789", 1L);
    }
}
