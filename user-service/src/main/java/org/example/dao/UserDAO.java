package org.example.dao;

import org.example.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, String> {
    User findByUserId(String userId);
}
