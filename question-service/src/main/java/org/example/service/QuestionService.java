package org.example.service;

import io.swagger.models.auth.In;
import org.example.dao.QuestionDAO;
import org.example.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private QuestionDAO questionDAO;

    @Autowired
    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }


    public Question getQuestionWithoutAnswer(String questionId) {

        //type, directionId, difficult, content
        Object[] objects = questionDAO.getQuestionWithoutAnswer(questionId).get(0);
        Question result = new Question();
        result.setType((Integer)objects[0]);
        result.setDirectionId((String)objects[1]);
        result.setDifficult((Integer) objects[2]);
        result.setContent((String)objects[3]);
        result.setOptions((String)objects[4]);

        return result;
    }

    public Question getQuestionWithAnswer(String questionId) {

        //type, directionId, difficult, content, answer, analyze, techPoint
        Object[] objects = questionDAO.getQuestionWithAnswer(questionId).get(0);

        Question result = new Question();
        result.setType((Integer)objects[0]);
        result.setDirectionId((String)objects[1]);
        result.setDifficult((Integer) objects[2]);
        result.setContent((String)objects[3]);
        result.setAnswer((String)objects[4]);
        result.setAnalyze((String)objects[5]);
        result.setTechPoint((String)objects[6]);

        return result;
    }



}
