package ch.wiss.quizbackend.controller;

import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/api/questions/{id}")
    public Question getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id);
    }
    @PostMapping("/api/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }
    @PutMapping("/api/questions/{id}")
    public Question updateQuestion(@PathVariable String id,
                                   @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }
    @DeleteMapping("/api/questions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
    }
}