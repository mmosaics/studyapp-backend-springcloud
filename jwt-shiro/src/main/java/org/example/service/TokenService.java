package org.example.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import org.example.pojo.AuthInfo;
import org.example.util.JwtUtil;
import org.example.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class TokenService {

    private AuthenticationService authenticationService;

    private RedisUtil redisUtil;

    private JwtUtil jwtUtil;

    @Autowired
    public TokenService(AuthenticationService authenticationService, RedisUtil redisUtil, JwtUtil jwtUtil) {
        this.authenticationService = authenticationService;
        this.redisUtil = redisUtil;
        this.jwtUtil = jwtUtil;
    }

    public AuthInfo getAuthInfo(HttpServletRequest request) {
        //获取token
        String token = getToken(request);
        //根据token获取loginName
        String loginName = getUserId(token);

        //根据userId获取redis中缓存的对象
        AuthInfo authInfo = (AuthInfo) redisUtil.get("loginName");


        if(authInfo == null) {

            AuthInfo newAuthInfo = authenticationService.getUserByLoginName(loginName);
            redisUtil.set(loginName, newAuthInfo);

        }

        return authInfo;

    }

    /**
     * 根据token内容获得userId
     * @param token
     * @return
     */
    public String getUserId(String token) {

        Claims claims = jwtUtil.decode(token);

        return (String)claims.get("loginName");
    }


    public String getToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("Authorization");
    }


    public String createToken(String iss, long ttl, Map<String, Object> map) {

        JwtUtil jwtUtil = new JwtUtil();

        return jwtUtil.encode(iss, ttl, map);
    }


    public boolean verify(String token) {

        JwtUtil jwtUtil = new JwtUtil();

        return jwtUtil.isVerify(token);

    }


}
