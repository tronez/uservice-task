package uservice.user;

import uservice.user.dto.NewEmailDTO;

class EmailFactory {

    static NewEmailDTO createMailForSaving() {
        return new NewEmailDTO("simple@gmail.com", 1L);
    }
}
