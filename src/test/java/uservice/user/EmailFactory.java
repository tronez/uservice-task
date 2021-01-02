package uservice.user;

import uservice.user.dto.EmailRequest;
import uservice.user.dto.NewEmailRequest;

class EmailFactory {

    static NewEmailRequest createMailForSaving() {
        return new NewEmailRequest("simple@gmail.com", 1L);
    }

    static EmailRequest createMailForUpdate() {
        return new EmailRequest("updatedEmail@gmail.com");
    }
}
