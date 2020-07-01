package org.example.service;

import org.apache.dubbo.config.annotation.Reference;
import org.example.dao.UserDAO;

import org.example.pojo.User;
import org.example.util.GlobalResult;
import org.example.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

    private UserDAO userDAO;
    private RedisUtil redisUtil;

    @Autowired
    public UserService(UserDAO userDAO, RedisUtil redisUtil) {
        this.userDAO = userDAO;
        this.redisUtil = redisUtil;
    }

    @Reference
    AuthService authService;

    //更新用户
    public User update(User user) {

        return userDAO.save(user);
    }

    //通过userId找到用户
    public User getUserByUserId(String userId) {
        return this.userDAO.findByUserId(userId);
    }


    public String getUserIdBySessionId(String sessionId) {

        Map<String, Object> map = authService.authUserInfo(sessionId);

        String userId = (String)map.get("userId");

        return userId;

    }
















    //更新用户昵称
    public User updateUser(User userParam) {
        User user = this.getUserByUserId(userParam.getUserId());
        if (user == null) {
            //用户不存在
            return null;
        } else {
            user.setSchool(userParam.getSchool());
            user.setDepartId(userParam.getDepartId());
            user.setIspublicdept(userParam.getIspublicdept());
            user.setOrgId(userParam.getOrgId());
            user.setIspublicorg(userParam.getIspublicorg());
            user.setIspublicpost(userParam.getIspublicpost());
            user.setMobilephone(userParam.getMobilephone());
            user.setEmail(userParam.getEmail());
            user.setIsverityemail(userParam.getIsverityemail());
            user.setIspublicemail(userParam.getIspublicemail());
            user.setAddress(userParam.getAddress());
            user.setIspublicaddress(userParam.getIspublicaddress());
            user.setNick(userParam.getNick());
            user.setName(userParam.getName());
            user.setUserpic(userParam.getUserpic());

            this.update(user);
            //用户存在
            return user;
        }
    }
}
