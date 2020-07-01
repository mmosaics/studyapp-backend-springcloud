package org.example.service;

import org.example.dao.TestDAO;
import org.example.pojo.Test;
import org.example.pojo.TestCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    private TestDAO testDAO;

    private TestCategoryService testCategoryService;

    private TestToQuestionService testToQuestionService;

    @Autowired
    public TestService(TestDAO testDAO, TestCategoryService testCategoryService, TestToQuestionService testToQuestionService) {
        this.testDAO = testDAO;
        this.testCategoryService = testCategoryService;
        this.testToQuestionService = testToQuestionService;
    }


    public Map<String, Object> getTestByTestId(String testId) {

        Test test = testDAO.findByTestId(testId);
        String categoryId = test.getCategoryId();

        TestCategory testCategory = testCategoryService.getTestCategoryByCategoryId(categoryId);
        TestCategory parentCategory = null;

        Map<String, Object> map = new HashMap<>();

        //清除无用数据
        test.setTestData(null);
        test.setUserId(null);
        test.setCreateTime(null);

        //此处存放test的基本信息
        map.put("test_info", test);

        //存放子分类
        map.put("child_category", testCategory);

        String parentId = testCategory.getParentId();

        if(parentId != null) {
            parentCategory = testCategoryService.getTestCategoryByCategoryId(parentId);
        }

        //存放父分类
        map.put("parent_category", parentCategory);

        List<Map<String, Object>> testToQuestionList = getQuestionInfoListByTestId(testId);

        //存放该试卷下的问题基本信息，不包括答案和题目，答案和题目需要根据ID获取
        map.put("question_info", testToQuestionList);

        //存放问题的总量，便于前端布局
        map.put("quetion_num", testToQuestionList.size());


        return map;


    }


    public List<Map<String, Object>> getQuestionInfoListByTestId(String testId) {

        //List<TestToQuestion> testToQuestionList = testToQuestionService.getTestToQuestionListByTestId(testId);
        List<Map<String, Object>> testToQuestionList = testToQuestionService.getPartOfTestToQuestionByTestId(testId);

        return testToQuestionList;
    }


}
