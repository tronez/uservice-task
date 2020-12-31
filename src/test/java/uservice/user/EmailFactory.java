package uservice.user;

import uservice.user.dto.NewEmailRequest;

class EmailFactory {

    static NewEmailRequest createMailForSaving() {
        return new NewEmailRequest("simple@gmail.com", 1L);
    }
}
