package org.example.controller;

import org.example.pojo.AuthInfo;
import org.example.service.AuthenticationService;
import org.example.service.LoginService;
import org.example.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class LoginController {


    private AuthenticationService authenticationService;
    private LoginService loginService;
    private TokenService tokenService;

    @Autowired
    public LoginController(AuthenticationService authenticationService, LoginService loginService, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.loginService = loginService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AuthInfo authInfo) {

        return loginService.loginByPassword(authInfo.getLoginName(), authInfo.getPassword());

    }


    @GetMapping("/check-login")
    public String checkLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {




    }





}
