package ch.wiss.quizbackend.service;


import ch.wiss.quizbackend.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class QuestionServiceTest {


    @Autowired
    private QuestionService questionService;


    @Test
    void getAllQuestionsReturnsData() {
        List<Question> question= questionService.getAllQuestions();
        assertFalse(question.isEmpty());
    }


    @Test
    void getQuestionByIdReturnsCorrectQuestion() {
        Question question= questionService.getQuestionById("1");
        assertNotNull(question);
        assertEquals("1", question.getId());
    }
}
