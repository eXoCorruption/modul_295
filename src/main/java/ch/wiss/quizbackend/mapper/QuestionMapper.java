package ch.wiss.quizbackend.mapper;


import ch.wiss.quizbackend.dto.QuestionFormDTO;
import ch.wiss.quizbackend.model.Question;


/**
 * Wandelt ein QuestionFormDTO in eine Question-Entity um.
 */
public class QuestionMapper {


    /**
     * Erzeugt eine Question-Entity aus den Formulardaten und einer id.
     * Die id stammt nicht aus dem DTO, sondern wird separat uebergeben:
     * beim Erstellen vom Server generiert, beim Ändern aus der URL.
     */
    public static Question toEntity(String id, QuestionFormDTO dto) {
        Question question = new Question();
        question.setId(id);
        question.setText(dto.text());
        question.setCategory(dto.category());
        question.setDifficulty(dto.difficulty());
        question.setAnswers(dto.answers());
        question.setCorrectAnswer(dto.correctAnswer());
        return question;
    }
}
