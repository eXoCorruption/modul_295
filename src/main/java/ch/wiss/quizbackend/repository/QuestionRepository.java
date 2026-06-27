package ch.wiss.quizbackend.repository;

import ch.wiss.quizbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Datenbank-Zugriff fuer {@link Question}-Objekte.
 * <p>
 * Erbt von {@link JpaRepository} und
 * erhält dadurch automatisch Methoden
 * wie save(), findById(), findAll(), count() und delete() - ganz ohne
 * eigene Implementierung.
 * </p>
 */
public interface QuestionRepository extends JpaRepository<Question, String> {

    List<Question> findByCategory(String category);
    List<Question> findByDifficulty(String difficulty);
    long countByCategory(String category);
    List<Question> findByCategoryAndDifficulty(String category, String difficulty);
}