package ch.wiss.quizbackend.service;

import ch.wiss.quizbackend.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    public List<Question> getAllQuestions() {
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
