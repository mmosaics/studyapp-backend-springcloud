package org.example.dao;

import org.example.pojo.TestCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCategoryDAO extends JpaRepository<TestCategory, String> {

    TestCategory findByTestcategoryId(String testCategoryId);



}
