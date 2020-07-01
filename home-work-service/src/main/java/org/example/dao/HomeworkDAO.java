package org.example.dao;

import org.example.pojo.Homework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkDAO extends JpaRepository<Homework, String> {
    Homework findByUsertohomeworkId(String usertohomeworkId);
    Page<Homework> findAllByUserId(String userID, Pageable page);
}
