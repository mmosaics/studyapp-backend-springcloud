package org.example.service;

import org.example.dao.TestCategoryDAO;
import org.example.pojo.TestCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 考试分类服务
 */
@Service
public class TestCategoryService {

    private TestCategoryDAO testCategoryDAO;

    @Autowired
    public TestCategoryService(TestCategoryDAO testCategoryDAO) {
        this.testCategoryDAO = testCategoryDAO;
    }

    TestCategory getTestCategoryByCategoryId(String testCategoryId) {
        return testCategoryDAO.findByTestcategoryId(testCategoryId);
    }

}
