package uservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uservice.user.domain.DomainFacade;
import uservice.user.dto.EmailDTO;
import uservice.user.dto.EmailRequest;
import uservice.user.dto.NewEmailRequest;
import uservice.user.dto.NewPhoneNumberRequest;
import uservice.user.dto.PhoneNumberRequest;
import uservice.user.dto.UserRequest;
import uservice.user.dto.PhoneNumberDTO;
import uservice.user.dto.UserResponse;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final DomainFacade facade;

    public UserController(DomainFacade facade) {
        this.facade = facade;
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) {

        final UserResponse savedUser = facade.saveUser(userRequest);
        final URI location = fromCurrentRequestAndAppend(savedUser.getId());

        return ResponseEntity
                .created(location)
                .body(savedUser);
    }

    @PostMapping("/email")
    public ResponseEntity<EmailDTO> addEmailToUser(@Valid @RequestBody NewEmailRequest newEmailRequest) {

        final EmailDTO savedEmail = facade.addEmailToUser(newEmailRequest);
        final URI location = fromCurrentRequestAndAppend(savedEmail.getId());

        return ResponseEntity
                .created(location)
                .body(savedEmail);
    }

    @PostMapping("/phoneNumber")
    public ResponseEntity<PhoneNumberDTO> addPhoneNumberToUser(@Valid @RequestBody NewPhoneNumberRequest newNumberRequest) {

        final PhoneNumberDTO savedPhoneNumber = facade.addPhoneNumberToUser(newNumberRequest);
        final URI location = fromCurrentRequestAndAppend(savedPhoneNumber.getId());

        return ResponseEntity
                .created(location)
                .body(savedPhoneNumber);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        final UserResponse dto = facade.findUserById(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable Long id) {

        facade.deleteUserById(id);
        return HttpStatus.OK;
    }

    @PatchMapping("/{userId}/email/{emailId}")
    public ResponseEntity<EmailDTO> updateEmail(@PathVariable Long userId,
                                                @PathVariable Long emailId,
                                                @RequestBody @Valid EmailRequest emailRequest) {

        final EmailDTO emailDTO = facade.updateUserEmail(userId, emailId, emailRequest.getEmail());
        return ResponseEntity.ok(emailDTO);
    }

    @PatchMapping("/{userId}/phoneNumber/{numberId}")
    public ResponseEntity<PhoneNumberDTO> updatePhoneNumber(@PathVariable Long userId,
                                                            @PathVariable Long numberId,
                                                            @RequestBody @Valid PhoneNumberRequest numberRequest) {

        final PhoneNumberDTO numberDTO = facade.updateUserPhoneNumber(userId, numberId, numberRequest.getPhoneNumber());
        return ResponseEntity.ok(numberDTO);
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserByLastName(@RequestParam(value = "lastname") String lastName) {

        final UserResponse dto = facade.findUserByLastName(lastName);
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
