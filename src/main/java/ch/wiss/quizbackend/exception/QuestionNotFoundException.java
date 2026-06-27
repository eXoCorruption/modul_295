package ch.wiss.quizbackend.exception;

/**
 * Wird geworfen, wenn keine Frage mit der gesuchten id existiert.
 */
public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(String id) {
        super("Keine Frage mit der id " + id + " gefunden!");
    }
}