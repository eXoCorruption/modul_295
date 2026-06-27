package ch.wiss.quizbackend.service;

import ch.wiss.quizbackend.dto.QuestionFormDTO;
import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void getAllQuestionsReturnsData() {
        List<Question> question = questionService.getAllQuestions();
        assertFalse(question.isEmpty());
    }

    @Test
    void getQuestionByIdReturnsCorrectQuestion() {
        Question question = questionService.getQuestionById("1");
        assertNotNull(question);
        assertEquals("1", question.getId());
    }

    @Test
    @Transactional
    void createQuestionPersistsToDb() {
        // Arrange: ein FormDTO ohne id
        QuestionFormDTO form = new QuestionFormDTO(
                "Wie heisst die Hauptstadt der Schweiz?",
                "Geografie",
                "leicht",
                List.of("Bern", "Zürich", "Genf", "Basel"),
                "Bern"
        );

        // Act: Frage über den Service erstellen
        Question saved = questionService.createQuestion(form);

        // Assert: Der Server hat eine id vergeben ...
        assertNotNull(saved.getId());
        // ... und die Frage ist über ihre id auffindbar
        assertTrue(questionRepository.findById(saved.getId()).isPresent());

        // Kein Aufräumen nötig: @Transactional rollt am Ende automatisch zurück.
    }

}