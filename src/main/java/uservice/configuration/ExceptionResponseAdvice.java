package uservice.configuration;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
class ExceptionResponseAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<List<String>> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> message = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity
                .badRequest()
                .body(message);
    }
}
