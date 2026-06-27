package ch.wiss.quizbackend;

import ch.wiss.quizbackend.model.Question;
import ch.wiss.quizbackend.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Füllt die Datenbank beim Programmstart mit Startdaten.
 * <p>
 * Der Seeder besitzt die Startdaten jetzt selbst und ist nicht mehr
 * vom QuestionService abhängig.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final QuestionRepository questionRepository;

    public DataSeeder(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (questionRepository.count() == 0) {
            questionRepository.saveAll(getStartQuestions());
            System.out.println("DataSeeder: " + questionRepository.count() + " Fragen in die DB geschrieben.");
        } else {
            System.out.println("DataSeeder: DB enthält bereits Daten, kein Seeding nötig.");
        }
    }

    /**
     * Liefert die Startfragen, mit denen eine leere Datenbank befüllt wird.
     */
    private List<Question> getStartQuestions() {
        return List.of(
                new Question(
                        "1",
                        "Welches Videospiel gilt als erstes kommerziell erfolgreiches Arcade-Spiel?",
                        "Gaming",
                        "leicht",
                        List.of("Pong", "Space Invaders", "Pac-Man", "Tetris"),
                        "Pong"
                ),
                new Question(
                        "2",
                        "In welchem Spiel spielt man als Gordon Freeman?",
                        "Gaming",
                        "leicht",
                        List.of("Half-Life", "Doom", "Quake", "Portal"),
                        "Half-Life"
                ),
                new Question(
                        "3",
                        "Welche Firma entwickelte Minecraft ursprünglich?",
                        "Gaming",
                        "leicht",
                        List.of("Mojang", "Valve", "Epic Games", "Blizzard"),
                        "Mojang"
                )
        );
    }
}

