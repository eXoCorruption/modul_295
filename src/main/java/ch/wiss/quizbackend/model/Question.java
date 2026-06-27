package ch.wiss.quizbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="questions")
public class Question {

    /**
     * Eindeutige ID der Frage. Wird manuell gesetzt (passend zum M294-Frontend).
     */
    @Id
    private String id;

    /**
     * Der Fragetext, der dem Nutzer angezeigt wird.
     */
    private String text;

    /** Themengebiet der Frage, z.B. "Sport" oder "Geografie". */
    private String category;

    /** Schwierigkeitsgrad, z.B. "leicht", "mittel" oder "schwer". */
    private String difficulty;

    /**
     * Die möglichen Antworten als einfache Liste von Texten. Mit
     * {@code @ElementCollection} legt Hibernate dafür die Tabelle
     * "question_answers" an. Die Liste wird {@code EAGER} geladen, weil
     * eine Frage ohne ihre Antworten nutzlos ist.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> answers;

    /** Die korrekte Antwort. Entspricht einem Eintrag aus {@link #answers}. */
    private String correctAnswer;

    /**
     * Parameterloser Konstruktor, den JPA/Hibernate zwingend benötigt.
     */
    public Question() {
    }

    /**
     * Konstruktor mit all den Parametern für das Quiz.
     * @param id
     * @param text
     * @param category
     * @param difficulty
     * @param answers
     * @param correctAnswer
     */
    public Question(String id, String text, String category, String difficulty, List<String> answers, String correctAnswer) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.difficulty = difficulty;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}