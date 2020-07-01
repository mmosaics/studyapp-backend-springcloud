package org.example.service;

import org.example.dao.TestToQuestionDAO;
import org.example.pojo.TestToQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestToQuestionService {


    private TestToQuestionDAO testToQuestionDAO;

    @Autowired
    public TestToQuestionService(TestToQuestionDAO testToQuestionDAO) {
        this.testToQuestionDAO = testToQuestionDAO;

    }


    public List<TestToQuestion> getTestToQuestionListByTestId(String testId) {
        return testToQuestionDAO.findAllByTestId(testId);
    }

    /**
     * 获取题目的部分信息，基本信息，用于题目列表的展示
     * @param testId
     * @return
     */
    public List<Map<String, Object>> getPartOfTestToQuestionByTestId(String testId) {

        List<Object[]> res = testToQuestionDAO.getTestInfoByTestId(testId);

        List<Map<String, Object>> result = new ArrayList<>();

        for(Object[] object: res) {

            Map<String, Object> map = new HashMap<>();

            map.put("questionId", object[0]);
            map.put("type", object[1]);
            map.put("point", object[2]);

            result.add(map);

        }

        return result;



    }



}
