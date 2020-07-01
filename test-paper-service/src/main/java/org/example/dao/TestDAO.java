package org.example.dao;

import org.example.pojo.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDAO extends JpaRepository<Test, String> {

    Test findByTestId(String testId);

}
