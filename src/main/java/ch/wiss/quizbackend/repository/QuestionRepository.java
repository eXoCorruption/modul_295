package ch.wiss.quizbackend.repository;

import ch.wiss.quizbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Datenbank-Zugriff fuer {@link Question}-Objekte.
 * <p>
 * Erbt von {@link JpaRepository} und
 * erhält dadurch automatisch Methoden
 * wie save(), findById(), findAll(), count() und delete() - ganz ohne
 * eigene Implementierung.
 */
public interface QuestionRepository extends JpaRepository<Question, String> {
}

