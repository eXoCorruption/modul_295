package ch.wiss.quizbackend.controller;

import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    // Hier passiert Dependency Injection:
    // Spring sieht, dass der Controller einen QuestionService braucht,
    // und reicht ihn beim Erstellen automatisch herein.
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/api/questions")
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();
    }
}