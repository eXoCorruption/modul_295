package ch.wiss.quizbackend;

import java.util.List;

public class Question {

    private String id;
    private String text;
    private String category;
    private String difficulty;
    private List<String> answers;
    private String correctAnswer;

    public Question(String id, String text, String category, String
            difficulty,List<String> answers, String correctAnswer) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.difficulty = difficulty;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getId() { return id; }
    public String getText() { return text; }
    public String getCategory() { return category; }
    public String getDifficulty() { return difficulty; }
    public List<String> getAnswers() { return answers; }
    public String getCorrectAnswer() { return correctAnswer; }
}