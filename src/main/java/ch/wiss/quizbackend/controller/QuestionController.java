package ch.wiss.quizbackend.controller;

import ch.wiss.quizbackend.dto.QuestionFormDTO;
import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.service.QuestionService;
import jakarta.validation.Valid;
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
    public Question createQuestion(@Valid @RequestBody QuestionFormDTO form) {
        return questionService.createQuestion(form);
    }


    @PutMapping("/api/questions/{id}")
    public Question updateQuestion(@PathVariable String id, @Valid @RequestBody QuestionFormDTO form) {
        return questionService.updateQuestion(id, form);
    }

    @DeleteMapping("/api/questions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable String id) {
        questionService.deleteQuestion(id);
    }


    @GetMapping("/api/questions/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    @GetMapping("/api/questions/difficulty/{difficulty}")
    public List<Question> getQuestionsByDifficulty(@PathVariable String difficulty) {
        return questionService.getQuestionsByDifficulty(difficulty);
    }

    /**
     * Gibt eine Liste von Fragen zurück, sortiert nach Kategorie,
     * falls mitgegeben und einer Limite für die Anzahl Fragen die returniert werden sollen.
     * <p>
     *     <b>Was passiert hier?</b>
     * <ul>
     *     <li>@RequestParam liest Werte aus der Query der URL (der Teil nach dem ?).</li>
     *     <li>required = false macht category optional – fehlt sie, ist der Wert null.</li>
     *     <li>defaultValue = "10" belegt count vor, falls der Client nichts angibt.</li>
     * </ul>
     * </p>
     */
    @GetMapping("/api/questions/random")
    public List<Question> getRandomQuestions(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String difficulty,
            @RequestParam(defaultValue = "10") int count) {
        return questionService.getRandomQuestions(category, difficulty, count);
    }


}