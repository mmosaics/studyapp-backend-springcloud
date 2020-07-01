package org.example.dao;


import org.example.pojo.AuthInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthInfoDAO extends JpaRepository<AuthInfo, String> {

    AuthInfo findByLoginName(String loginName);

    AuthInfo findAuthInfoById(String Id);
    
}
