package uservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uservice.user.domain.UserFacade;
import uservice.user.dto.EmailResponse;
import uservice.user.dto.NewEmailRequest;
import uservice.user.dto.NewPhoneNumberRequest;
import uservice.user.dto.UserRequest;
import uservice.user.dto.PhoneNumberResponse;
import uservice.user.dto.UserResponse;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) {

        final UserResponse savedUser = userFacade.saveUser(userRequest);
        final URI location = fromCurrentRequestAndAppend(savedUser.getId());

        return ResponseEntity
                .created(location)
                .body(savedUser);
    }

    @PostMapping("/email")
    public ResponseEntity<EmailResponse> addEmailToUser(@Valid @RequestBody NewEmailRequest newEmailRequest) {

        final EmailResponse savedEmail = userFacade.addEmailToUser(newEmailRequest);
        final URI location = fromCurrentRequestAndAppend(savedEmail.getId());

        return ResponseEntity
                .created(location)
                .body(savedEmail);
    }

    @PostMapping("/phoneNumber")
    public ResponseEntity<PhoneNumberResponse> addPhoneNumberToUser(@Valid @RequestBody NewPhoneNumberRequest newPhoneNumberRequest) {

        final PhoneNumberResponse savedPhoneNumber = userFacade.addPhoneNumberToUser(newPhoneNumberRequest);
        final URI location = fromCurrentRequestAndAppend(savedPhoneNumber.getId());

        return ResponseEntity
                .created(location)
                .body(savedPhoneNumber);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        final UserResponse dto = userFacade.findUserById(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable Long id) {

        userFacade.deleteUserById(id);
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserByLastName(@RequestParam(value = "lastname") String lastName) {

        final UserResponse dto = userFacade.findUserByLastName(lastName);
        return ResponseEntity.ok(dto);
    }

    private URI fromCurrentRequestAndAppend(Long idToAppend) {

        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idToAppend)
                .toUri();
    }
}
