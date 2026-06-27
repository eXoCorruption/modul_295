package ch.wiss.quizbackend.service;

import ch.wiss.quizbackend.dto.QuestionFormDTO;
import ch.wiss.quizbackend.mapper.QuestionMapper;
import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service-Schicht für Fragen. Kapselt die Geschäftslogik und
 * vermittelt zwischen Controller und Repository.
 */
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Liefert alle Fragen aus der Datenbank.
     * @return
     */
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    /**
     * Liefert eine einzelne Frage anhand ihrer ID, oder null, wenn es sie nicht gibt.
     * @param id
     * @return
     */
    public Question getQuestionById(String id) {
        return questionRepository.findById(id).orElse(null);
    }

    /**
     * Erstellt eine neue Frage. Die id wird hier vom Server erzeugt,
     * damit der Client sich keine eindeutige id ausdenken muss.
     * @param form
     * @return
     */
    public Question createQuestion(QuestionFormDTO form) {
        String id = UUID.randomUUID().toString();
        Question question = QuestionMapper.toEntity(id, form);
        return questionRepository.save(question);
    }


    /**
     * Aktualisiert eine bestehende Frage. Die id stammt aus der URL
     * und ist damit die einzige Quelle der Wahrheit.
     * @param id
     * @param form
     * @return
     */
    public Question updateQuestion(String id, QuestionFormDTO form) {
        Question question = QuestionMapper.toEntity(id, form);
        return questionRepository.save(question);
    }


    /**
     * Löscht die Frage mit der angegebenen id aus der Datenbank.
     */
    public void deleteQuestion(String id) {
        questionRepository.deleteById(id);
    }
}