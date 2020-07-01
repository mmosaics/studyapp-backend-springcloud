package org.example.service;

import org.example.dao.UserToCourseInfoDAO;
import org.example.pojo.UserToCourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserToCourseInfoService {
    
    private UserToCourseInfoDAO userToCourseInfoDAO;

    @Autowired
    public UserToCourseInfoService(UserToCourseInfoDAO userToCourseInfoDAO) {
        this.userToCourseInfoDAO = userToCourseInfoDAO;
    }

    List<UserToCourseInfo> getUserToCourseInfoByUserId(String userId) {
        return userToCourseInfoDAO.findAllByUserId(userId);
    }
    
}
