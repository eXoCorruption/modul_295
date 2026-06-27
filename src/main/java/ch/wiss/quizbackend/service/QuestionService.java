package ch.wiss.quizbackend.service;

import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Liefert alle Fragen aus der Datenbank.
     */
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    /**
     * Liefert eine einzelne Frage anhand ihrer ID, oder null, wenn es sie nicht gibt.
     */
    public Question getQuestionById(String id) {
        return questionRepository.findById(id).orElse(null);
    }
}