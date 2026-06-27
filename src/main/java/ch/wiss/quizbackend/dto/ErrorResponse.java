package ch.wiss.quizbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Einheitliches Format für Error-Antworten der API.
 * fieldErrors werden nur bei Validierungsfehlern befüllt.
 * @param timestamp
 * @param status
 * @param message
 * @param fieldErrors
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String message,
        Map<String, String> fieldErrors
) {}