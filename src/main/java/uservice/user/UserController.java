package uservice.user;

import org.springframework.web.bind.annotation.RestController;
import uservice.user.domain.UserService;

@RestController
class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

}
