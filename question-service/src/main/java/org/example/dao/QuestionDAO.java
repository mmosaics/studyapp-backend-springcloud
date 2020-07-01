package org.example.dao;

import org.example.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDAO extends JpaRepository<Question, String> {

    Question findByQuestionId(String questionId);

    @Query("select type, directionId, difficult, content, options from Question where questionId=?1")
    List<Object[]> getQuestionWithoutAnswer(String questionId);

    @Query("select type, directionId, difficult, content, answer, analyze, techPoint from Question where questionId=?1")
    List<Object[]> getQuestionWithAnswer(String questionId);


}
