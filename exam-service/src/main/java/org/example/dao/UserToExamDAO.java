package org.example.dao;

import org.apache.catalina.User;
import org.example.pojo.UserToExam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserToExamDAO extends JpaRepository<UserToExam, String> {

    Page<UserToExam> findAllByUserId(String userId, Pageable pageable);

    @Query("select status, examId, starttime, endtime, socre, objectivescore, rightcount, errorcount " +
            "from UserToExam where userId=?1")
    List<Map<String, Object>> getUserExams(String userId);

    UserToExam findByUsertoexamId(String userToExamId);

}
