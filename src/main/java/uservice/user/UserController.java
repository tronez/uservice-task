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
import uservice.user.dto.EmailDto;
import uservice.user.dto.NewEmailDTO;
import uservice.user.dto.NewPhoneNumberDTO;
import uservice.user.dto.PhoneNumberDto;
import uservice.user.dto.UserDto;

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
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto dto) {

        final UserDto savedUser = userFacade.saveUser(dto);
        final URI location = fromCurrentRequestAndAppend(savedUser.getId());

        return ResponseEntity
                .created(location)
                .body(savedUser);
    }

    @PostMapping("/email")
    public ResponseEntity<EmailDto> addEmailToUser(@Valid @RequestBody NewEmailDTO emailDto) {

        final EmailDto savedEmail = userFacade.addEmailToUser(emailDto);
        final URI location = fromCurrentRequestAndAppend(savedEmail.getId());

        return ResponseEntity
                .created(location)
                .body(savedEmail);
    }

    @PostMapping("/phoneNumber")
    public ResponseEntity<PhoneNumberDto> addPhoneNumberToUser(@Valid @RequestBody NewPhoneNumberDTO phoneNumberDto) {

        final PhoneNumberDto savedPhoneNumber = userFacade.addPhoneNumberToUser(phoneNumberDto);
        final URI location = fromCurrentRequestAndAppend(savedPhoneNumber.getId());

        return ResponseEntity
                .created(location)
                .body(savedPhoneNumber);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {

        final UserDto dto = userFacade.findUserById(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable Long id) {

        userFacade.deleteUserById(id);
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserByLastName(@RequestParam(value = "lastname") String lastName) {

        final UserDto dto = userFacade.findUserByLastName(lastName);
        return ResponseEntity.ok(dto);
    }

    private URI fromCurrentRequestAndAppend(Long variableToAppend) {

        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(variableToAppend)
                .toUri();
    }
}
