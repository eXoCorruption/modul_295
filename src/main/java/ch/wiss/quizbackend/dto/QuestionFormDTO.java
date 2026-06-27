package ch.wiss.quizbackend.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.util.List;


public record QuestionFormDTO(
        @NotBlank(message = "darf nicht leer sein.")
        String text,


        @NotBlank(message = "darf nicht leer sein.")
        String category,


        @NotBlank
        @Pattern(regexp = "leicht|mittel|schwer", message = "Erlaubt sind nur: leicht, mittel oder schwer.")
        String difficulty,


        @NotNull
        @Size(min = 2, message = "Quizfrage braucht mind. 2 Antworten.")
        List<String> answers,


        @NotBlank(message = "darf nicht leer sein.")
        String correctAnswer
) {}