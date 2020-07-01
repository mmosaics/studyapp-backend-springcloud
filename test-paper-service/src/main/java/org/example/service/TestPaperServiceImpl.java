package org.example.service;

import org.apache.dubbo.config.annotation.Service;
import org.example.dao.TestDAO;
import org.example.dao.TestToQuestionDAO;
import org.example.pojo.TestToQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class TestPaperServiceImpl implements TestPaperService{

    private TestService testService;
    private TestToQuestionDAO testToQuestionDAO;
    private TestToQuestionService testToQuestionService;

    @Autowired
    public TestPaperServiceImpl(TestService testService, TestToQuestionDAO testToQuestionDAO, TestToQuestionService testToQuestionService) {
        this.testService = testService;
        this.testToQuestionDAO = testToQuestionDAO;
        this.testToQuestionService = testToQuestionService;
    }


    /**
     * 获取试卷的一些基本信息，其中还包括了试卷下面有哪些问题
     * @param testId
     * @return
     */
    @Override
    public Map<String, Object> getTestByTestId(String testId) {

        return testService.getTestByTestId(testId);

    }

    /**
     * 获取详细的问题列表，其中会包含选项等东西
     * @param testId
     * @return
     */
    @Override
    public List<Map<String, Object>> getQuestionInfoListByTestId(String testId) {

        return testService.getQuestionInfoListByTestId(testId);

    }

    /**
     * 获取数据库中这张表 t_cc_testquestion 中试卷，和题目和分数的关系
     * @param testQuestionId
     * @return
     */
    @Override
    public Map<String, Object> getTestQuestionInfo(String testQuestionId) {

        TestToQuestion testToQuestion = testToQuestionDAO.findByTestquestionId(testQuestionId);
        Map<String, Object> map = new HashMap<>();

        map.put("point", testToQuestion.getPoint());

        return map;

    }

    /**
     * 获取部分问题数据，主要是取得每道题的分数
     * @param testId
     * @return
     */
    public List<Map<String, Object>> getPartOfTestToQuestionByTestId(String testId) {

        return testToQuestionService.getPartOfTestToQuestionByTestId(testId);

    }


}
