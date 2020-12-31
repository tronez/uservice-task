package uservice.user;

import uservice.user.dto.EmailResponse;
import uservice.user.dto.PhoneNumberResponse;
import uservice.user.dto.UserResponse;

import java.util.ArrayList;
import java.util.List;

class UserFactory {

    static UserResponse createUserForSaving() {
        final List<EmailResponse> emailResponse = new ArrayList<>();
        emailResponse.add(new EmailResponse(null, "jdoe@gmail.com", null));

        final List<PhoneNumberResponse> phoneNumberResponse = new ArrayList<>();
        phoneNumberResponse.add(new PhoneNumberResponse(null, "111111111", null));

        return new UserResponse(null, "Jon", "Doe", emailResponse, phoneNumberResponse);
    }
}
