package uservice.user;

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
import uservice.user.domain.UserService;
import uservice.user.dto.UserDto;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto dto) {

        final UserDto savedUser = service.save(dto);

        URI resource = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity
                .created(resource)
                .body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {

        final UserDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<UserDto> getByLastName(@RequestParam(value = "lastname") String lastName) {

        final UserDto dto = service.findByLastName(lastName);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
