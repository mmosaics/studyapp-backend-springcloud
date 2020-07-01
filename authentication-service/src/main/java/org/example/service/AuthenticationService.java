package org.example.service;

import org.apache.shiro.subject.Subject;
import org.example.dao.AuthInfoDAO;
import org.example.pojo.AuthInfo;
import org.example.util.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private AuthInfoDAO authInfoDAO;

    @Autowired
    public AuthenticationService(AuthInfoDAO authInfoDAO) {
        this.authInfoDAO = authInfoDAO;
    }

    //更新用户信息
    public AuthInfo update(AuthInfo authInfo) {
       return authInfoDAO.save(authInfo);
    }

    //添加新用户
    public AuthInfo add(AuthInfo authInfo) {
        return authInfoDAO.save(authInfo);
    }

    //根据用户名返回用户信息
    public AuthInfo getUserByLoginName(String loginName) {
        return authInfoDAO.findByLoginName(loginName);
    }

    //判断用户名是否已经被注册
    public boolean isExist(String loginName) {
        AuthInfo authInfo = authInfoDAO.findByLoginName(loginName);
        if(authInfo == null)
            return false;
        else
            return true;
    }

    public Boolean authRequest(String sessionId) {

        Subject authSubject = new Subject.Builder().sessionId(sessionId).buildSubject();

        if(authSubject.isAuthenticated())
            return true;
        else
            return false;

    }



}
