package org.example.service;

import com.hlr.constant.Constant;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Reference
    AuthService authService;

    public String getUserIdBySession(String session) {

        return (String)authService.authUserInfo(session).get(Constant.USER_ID_STRING);

    }

}
