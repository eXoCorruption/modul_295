package ch.wiss.quizbackend.dto;

import java.util.List;

public record QuestionFormDTO(
        String text,
        String category,
        String difficulty,
        List<String> answers,
        String correctAnswer
) {}
