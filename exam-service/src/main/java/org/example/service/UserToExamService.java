package org.example.service;

import org.example.dao.UserToExamDAO;
import org.example.pojo.UserToExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserToExamService {

    private UserToExamDAO userToExamDAO;

    private int pageNum = 10;

    @Autowired
    public UserToExamService(UserToExamDAO userToExamDAO) {
        this.userToExamDAO = userToExamDAO;
    }

    public Page<UserToExam> getExamsByUserId(String userId, int start) {
        Sort sort = new Sort(Sort.Direction.ASC, "starttime");
        Pageable pageable = PageRequest.of(start, pageNum, sort);
        Page<UserToExam> userToExams =  userToExamDAO.findAllByUserId(userId, pageable);
        for(UserToExam userToExam : userToExams.getContent()) {
            userToExam.setAnswer(null);
            userToExam.setUsertoexamId(null);
            userToExam.setArrangetestId(null);
            userToExam.setUserId(null);
        }
        return userToExams;

    }


    public List<Map<String, Object>> getExamsByUserIdTwo(String userId) {
        return userToExamDAO.getUserExams(userId);
    }


    public UserToExam getUserToExamById(String examId) {
        return userToExamDAO.findByUsertoexamId(examId);
    }



}
