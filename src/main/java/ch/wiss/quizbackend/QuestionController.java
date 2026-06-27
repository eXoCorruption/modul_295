package ch.wiss.quizbackend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class QuestionController {

    private final List<Question> questions = List.of(
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

    @GetMapping("/api/questions")
    public List<Question> getQuestions() {
        return questions;
    }

    @GetMapping("/api/questions/{id}")
    public Question getQuestionById(@PathVariable String id) {
        return questions.stream()
                .filter(q -> q.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Frage mit ID " + id + " nicht gefunden"
                ));
    }
}
