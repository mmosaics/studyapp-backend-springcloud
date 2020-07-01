package org.example.controller;

import com.hlr.constant.Constant;
import com.hlr.utils.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.util.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Api(tags = "用户信息服务，如获取和修改昵称、邮箱等")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息", notes = "此处不需要任何参数，只需要在HTTP的header处带上用户的Session Key")
    public GlobalResult getUserInfo(HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getHeader(Constant.AUTH_HEADER);

        User user = userService.getUserByUserId(userService.getUserIdBySessionId(sessionId));

        Map<String, Object> res = new HashMap<>();
        res.put("userInfo", user);

        return GlobalResult.ok(res);

    }

    @PutMapping("/info")
    @ApiOperation(value = "修改用户信息", notes = "以json对象的形式传参，接收参数为RequestBody")
    public GlobalResult updateUserInfo(@RequestBody User userParam, HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getHeader(Constant.AUTH_HEADER);

        String userId = userService.getUserIdBySessionId(sessionId);

        User modifyUser = userService.getUserByUserId(userId);

        modifyUser.setNick(userParam.getNick());

        User res = userService.update(modifyUser);

        return  GlobalResult.ok(res);

    }


}
