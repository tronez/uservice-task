package uservice.user;

import uservice.user.dto.UserRequest;

class UserFactory {

    static UserRequest createUserForSaving() {
        return new UserRequest("Jon", "Doe");
    }
}
