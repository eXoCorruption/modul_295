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
    /**
     * Speichert eine neue Frage in der Datenbank und gibt die
     * gespeicherte Frage zurück.
     */
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }


    /**
     *  Aktualisiert eine bestehende Frage. Die id aus dem Pfad ist
     * massgebend und wird auf die Frage gesetzt, bevor gespeichert wird.
     */
    public Question updateQuestion(String id, Question question) {
        question.setId(id);
        return questionRepository.save(question);
    }


    /**
     * Löscht die Frage mit der angegebenen id aus der Datenbank.
     */
    public void deleteQuestion(String id) {
        questionRepository.deleteById(id);
    }

}