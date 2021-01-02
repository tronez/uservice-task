package uservice.user;

import uservice.user.dto.EmailRequest;
import uservice.user.dto.NewPhoneNumberRequest;
import uservice.user.dto.PhoneNumberRequest;

class PhoneNumberFactory {

    static NewPhoneNumberRequest createPhoneNumberForSaving() {
        return new NewPhoneNumberRequest("666456789", 1L);
    }

    static PhoneNumberRequest createPhoneNumberForUpdate() {
        return new PhoneNumberRequest("123456666");
    }
}
