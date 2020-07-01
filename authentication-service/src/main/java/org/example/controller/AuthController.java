package org.example.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.example.pojo.AuthInfo;
import org.example.service.AuthenticationService;
import org.example.util.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import org.apache.shiro.subject.Subject;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Api("用户的登录、注册等服务")
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @ApiIgnore
    @GetMapping("/test")
    public String test() {
        return "Hello,World";
    }


    @ApiOperation(value = "注册接口", notes = "发送用户名的用户名和密码")
    @ApiImplicitParams({@ApiImplicitParam(name = "AuthInfo", value = "认证信息", required = true, dataType = "application/json", paramType = "RequestBody")})
    @PostMapping("/register")
    @CrossOrigin
    public Object register(@RequestBody AuthInfo authInfo) {
        String name = authInfo.getLoginName();
        String password = authInfo.getPassword();
        name = HtmlUtils.htmlEscape(name);
        authInfo.setLoginName(name);

        boolean exist = authenticationService.isExist(name);

        if(exist) {
            String message = "username has been used";
            return GlobalResult.errorMsg(message);
        }


        String salt = new SecureRandomNumberGenerator().nextBytes().toString();

        int times = 2;
        String algorithmName = "SHA-256";

        String encodePassword = new SimpleHash(algorithmName, password, salt, times).toString();

        authInfo.setSalt(salt);
        authInfo.setPassword(encodePassword);
        authInfo.setHasLogin(0);

        Date now = DateUtil.parse(DateUtil.now());
        authInfo.setCreateTime(now);

        authenticationService.add(authInfo);



        return GlobalResult.ok("register success", null);

    }

    @ApiOperation(value = "登录接口", notes = "使用方式同注册接口几乎一样，登录成功后返回SessionID")
    @PostMapping("/login")
    @CrossOrigin
    public Object login(@RequestBody AuthInfo authInfoParam, HttpSession httpSession) {


        //获取用户名
        String name = authInfoParam.getLoginName();
        name = HtmlUtils.htmlEscape(name);

        //获取用户
        AuthInfo authInfo = authenticationService.getUserByLoginName(authInfoParam.getLoginName());
        if(authInfo.getHasLogin() == 0)
            authInfo.setHasLogin(1);

        //获取Subject对象，由shiro定义
        Subject subject = SecurityUtils.getSubject();

        //用户名和密码封装到token交给Realm进行验证
        UsernamePasswordToken token = new UsernamePasswordToken(name, authInfoParam.getPassword());


        try {
            subject.login(token);

            String session_str = subject.getSession().getId().toString();

            Map<String, String> result = new HashMap<>();
            result.put("token", session_str);
            GlobalResult globalResult = GlobalResult.build(200, "login success", result);
            return globalResult;
        } catch (ExcessiveAttemptsException excessiveAttemptsException) {
            String message = "login excessive attempt";
            return GlobalResult.errorMsg(message);
        } catch (AuthenticationException authenticationException) {
            String message = "username or password is not correct";
            return GlobalResult.errorMsg(message);
        }
    }

    @CrossOrigin
    @GetMapping("/logout")
    public GlobalResult logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            subject.logout();
        return GlobalResult.ok("logout success");
    }

    @GetMapping("/check-login")
    public Object checkLogin(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            return GlobalResult.ok();
        else
            return GlobalResult.errorMsg("not login");
    }

    @GetMapping("/echo/{msg}")
    public String echo(@PathVariable("msg") String msg) {
        return msg;
    }


    @GetMapping("/session/{sessionId}")
    public Boolean auth(@PathVariable("sessionId") String sessionId) {

        return authenticationService.authRequest(sessionId);

    }

}
