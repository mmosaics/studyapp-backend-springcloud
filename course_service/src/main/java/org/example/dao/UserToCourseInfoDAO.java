package org.example.dao;

import org.example.pojo.UserToCourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserToCourseInfoDAO extends JpaRepository<UserToCourseInfo, String> {

    List<UserToCourseInfo> findAllByUserId(String userId);


}
