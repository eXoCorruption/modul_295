package ch.wiss.quizbackend.controller;

import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ch.wiss.quizbackend.dto.QuestionFormDTO;


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

    /**
     * Gibt alle Fragen zurück
     * @return Returniert eine Liste mit allen Fragen
     */
    @GetMapping("/api/questions")
    public List<Question> getQuestions() {
        return questionService.getAllQuestions();

    }

    /**
     * Gibt eine eine einzelne Frage zurück
     * @param id Parameter id der geforderten Frage
     * @return Returniert die Frage mit der gewünschten id
     */
    @GetMapping("/api/questions/{id}")
    public Question getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/api/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public Question createQuestion(@RequestBody QuestionFormDTO form) {
        return questionService.createQuestion(form);
    }


    @PutMapping("/api/questions/{id}")
    public Question updateQuestion(@PathVariable String id, @RequestBody QuestionFormDTO form) {
        return questionService.updateQuestion(id, form);
    }

    @DeleteMapping("/api/questions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
    }


}