package ch.wiss.quizbackend.service;

import ch.wiss.quizbackend.dto.QuestionFormDTO;
import ch.wiss.quizbackend.exception.QuestionNotFoundException;
import ch.wiss.quizbackend.mapper.QuestionMapper;
import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
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
        if(!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException(id);
        }

        Question question = QuestionMapper.toEntity(id, form);
        return questionRepository.save(question);
    }


    /**
     * Löscht die Frage mit der angegebenen id aus der Datenbank.
     */
    public void deleteQuestion(String id) {
        if(!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException(id);
        }
        questionRepository.deleteById(id);
    }

    /**
     * Liefert alle Fragen einer bestimmten Kategorie.
     * @param category
     * @return
     */
    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    /**
     * Liefert alle Freagen einer bestimmten Schwierigkeit.
     * @param difficulty
     * @return
     */
    public List<Question> getQuestionsByDifficulty(String difficulty) {
        return questionRepository.findByDifficulty(difficulty);
    }


//    public List<Question> getRandomQuestions(String category, int count) {
//        List<Question> pool = (category == null) ? questionRepository.findAll() : questionRepository.findByCategory(category);
//
//        List<Question> shuffledPool = new ArrayList<>(pool);
//        Collections.shuffle(shuffledPool);
//
//        return shuffledPool.stream().limit(count).toList();
//    }
//
//    public List<Question> getRandomQuestions(String difficulty, int count) {
//        List<Question> pool = (difficulty == null) ? questionRepository.findAll() : questionRepository.findByDifficulty(difficulty);
//
//        List<Question> shuffledPool = new ArrayList<>(pool);
//        Collections.shuffle(shuffledPool);
//
//        return shuffledPool.stream().limit(count).toList();
//    }


    public List<Question> getRandomQuestions(String category, String difficulty, int count) {
        List<Question> pool;

        if (category != null && difficulty != null) {
            pool = questionRepository.findByCategoryAndDifficulty(category, difficulty);
        } else if (category != null) {
            pool = questionRepository.findByCategory(category);
        } else if (difficulty != null) {
            pool = questionRepository.findByDifficulty(difficulty);
        } else {
            pool = questionRepository.findAll();
        }

        List<Question> shuffledPool = new ArrayList<>(pool);
        Collections.shuffle(shuffledPool);
        return shuffledPool.stream().limit(count).toList();
    }

//    public List<Question> getRandomQuestions(String category, String difficulty, int count) {
//        List<Question> pool = Optional.ofNullable(category)
//                .flatMap(cat -> Optional.ofNullable(difficulty)
//                    .map(diff -> questionRepository.findByCategoryAndDifficulty(cat, diff))
//                    .or(() -> Optional.ofNullable(questionRepository.findByCategory(cat)))
//                )
//                .orElseGet(() -> Optional.ofNullable(difficulty)
//                        .map(questionRepository::findByDifficulty)
//                        .orElseGet(questionRepository::findAll)
//                );
//
//        List<Question> shuffledPool = new ArrayList<>(pool);
//        Collections.shuffle(shuffledPool);
//
//        return shuffledPool.stream().limit(count).toList();
//    }

}