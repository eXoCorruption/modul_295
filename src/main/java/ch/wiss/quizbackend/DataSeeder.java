package ch.wiss.quizbackend;

import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

/**
 * Füllt die Datenbank beim Start mit den Fragen aus questions.json,
 * aber nur dann, wenn die Tabelle noch leer ist.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final QuestionRepository questionRepository;
    private final ObjectMapper objectMapper;


    /**
     * Neu mit ObjectMapper für das auslesen der Json-Datei
     */
    public DataSeeder(QuestionRepository questionRepository,  ObjectMapper objectMapper) {
        this.questionRepository = questionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (questionRepository.count() == 0) {
            // wir greifen auf die question.json Datei zu
            ClassPathResource resource = new ClassPathResource("questions.json");

            try (InputStream inputStream = resource.getInputStream()) {
                // wir lesen die Werte aus dem InputStream und wandeln sie in eine Liste vom Typ Question um
                List<Question> questions = objectMapper.readValue(inputStream, new TypeReference<List<Question>>()
                {});
                // wir speichern nun die Liste der Question die wir aus der Json-Datei gelesen haben
                questionRepository.saveAll(questions);
                System.out.println("DataSeeder: " + questionRepository.count() + " Fragen in die DB geschrieben.");
            }

        } else {
            System.out.println("DataSeeder: DB enthält bereits Daten, kein Seeding nötig.");
        }
    }
}