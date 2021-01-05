package uservice.user;

import uservice.user.dto.EmailDTO;
import uservice.user.dto.NewEmailRequest;

class EmailFactory {

    static NewEmailRequest createMailForSaving() {
        return new NewEmailRequest("simple@gmail.com", 1L);
    }

    static EmailDTO createMailForUpdate() {
        return new EmailDTO(1L,"updatedEmail@gmail.com", 1L);
    }
}
