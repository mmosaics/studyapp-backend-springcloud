package org.example.service;

import cn.hutool.core.date.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.example.common.enums.LoginType;
import org.example.pojo.AuthInfo;
import org.example.shiro.token.CustomizedToken;
import org.example.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    private AuthenticationService authenticationService;

    private RedisUtil redisUtil;

    private TokenService tokenService;

    @Autowired
    public LoginService(AuthenticationService authenticationService, RedisUtil redisUtil, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.redisUtil = redisUtil;
        this.tokenService = tokenService;
    }

    public Map<String, Object> loginByPassword(String username, String password) {

        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.生成Token
        CustomizedToken token = new CustomizedToken(username, password, LoginType.PASSWORD_LOGIN_TYPE.toString());
        //3.登录
        try {
            subject.login(token);
            return returnLoginInitParam(username);
        } catch (IncorrectCredentialsException e) {
            return null;
        }

    }

    private Map<String, Object> returnLoginInitParam(String loginname) {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        AuthInfo authInfo = authenticationService.getUserByLoginName(loginname);

        data.put("loginName", authInfo.getLoginName());

        String token = tokenService.createToken(authInfo.getId(), 60*60*1000, data);

        result.put("token", token);

        return result;

    }

}
