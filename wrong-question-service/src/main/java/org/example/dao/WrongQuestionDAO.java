package org.example.dao;

import org.example.pojo.WrongQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WrongQuestionDAO extends JpaRepository<WrongQuestion, String> {

    Page<WrongQuestion> findAllByUserId(String userId, Pageable pageable);


}
