package org.example.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.example.pojo.AuthInfo;
import org.example.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

public class JPARealm extends AuthorizingRealm {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        String userName = authenticationToken.getPrincipal().toString();
        AuthInfo authInfo = authenticationService.getUserByLoginName(userName);
        String passwordAuth = authInfo.getPassword();
        String salt = authInfo.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authInfo, passwordAuth, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;

    }



}

