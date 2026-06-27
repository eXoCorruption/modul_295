package ch.wiss.quizbackend;

import ch.wiss.quizbackend.repository.QuestionRepository;
import ch.wiss.quizbackend.service.QuestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Füllt die Datenbank beim Programmstart mit Startdaten.
 * <p>
 * Die Daten werden nicht neu erfunden, sondern aus dem bestehenden
 * {@link QuestionService} übernommen und in die DB geschrieben.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final QuestionService questionService;
    private final QuestionRepository questionRepository;

    public DataSeeder(QuestionService questionService,
                      QuestionRepository questionRepository) {
        this.questionService = questionService;
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) {
        // Nur seeden, wenn die Tabelle leer ist,sonst bei jedem Neustart Duplikate.
        if (questionRepository.count() == 0) {
            questionRepository.saveAll(questionService.getAllQuestions());
            System.out.println("DataSeeder: " + questionRepository.count()
                    + " Fragen in die DB geschrieben.");
        } else {
            System.out.println("DataSeeder: DB enthält bereits Daten,  kein Seeding nötig.");
        }
    }
}
