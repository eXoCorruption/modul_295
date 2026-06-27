package ch.wiss.quizbackend.exception;

import ch.wiss.quizbackend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    /**
     * Behandelt eine nicht gefundene Frage und liefert 404.
     */
    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(QuestionNotFoundException ex) {
        return new ErrorResponse(LocalDateTime.now(), 404, ex.getMessage(), Map.of());
    }

    /**
     * Behandelt fehlgeschlagene Validierung und liefert 400
     * samt einer Map Feld -> Fehlermeldung.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        return new ErrorResponse(LocalDateTime.now(), 400, "Validierung fehlgeschlagen", fieldErrors);
    }
}