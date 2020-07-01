package org.example.shiro.realm;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.example.shiro.token.JwtToken;
import org.example.util.JwtUtil;

public class JwtRealm extends AuthorizingRealm {

    /**
     * 标识这个Realm识专门用来验证JwtToken
     */

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String jwt = (String) token.getPrincipal();
        if(jwt == null) {
            throw new NullPointerException("jwtToken 不允许为空");
        }

        //判断
        JwtUtil jwtUtil = new JwtUtil();
        if(!jwtUtil.isVerify(jwt)) {
            throw new UnknownAccountException();
        }
        //验证用户是否存在
        String username = (String) jwtUtil.decode(jwt).get("loginName");
        System.out.println("在使用token登录" + username);
        return new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");

    }
}
