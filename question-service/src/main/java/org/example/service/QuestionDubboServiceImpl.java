package org.example.service;

import org.apache.dubbo.config.annotation.Service;
import org.example.dao.QuestionDAO;
import org.example.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Service
public class QuestionDubboServiceImpl implements QuestionDubboService{

    private QuestionDAO questionDAO;

    @Autowired
    public QuestionDubboServiceImpl(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    @Override
    public Map<String, Object> getFullQuestionInfo(String questionId) {

        Map<String, Object> map = new HashMap<>();

        Question question = questionDAO.findByQuestionId(questionId);

        map.put("rightAnswer", question.getAnswer());

        return map;

    }
}
