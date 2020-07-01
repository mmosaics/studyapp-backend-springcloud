package org.example.dao;

import org.example.pojo.TestToQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestToQuestionDAO extends JpaRepository<TestToQuestion, String> {

    TestToQuestion findByTestquestionId(String testQuestionId);

    List<TestToQuestion> findAllByTestId(String testId);

    @Query("select questionId, type, point from TestToQuestion where testId=?1")
    List<Object[]> getTestInfoByTestId(String testId);

}
