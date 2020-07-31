package org.example.shiro.realm;


import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.example.pojo.AuthInfo;
import org.example.service.AuthenticationService;
import org.example.shiro.token.CustomizedToken;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordRealm extends AuthorizingRealm {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomizedToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomizedToken customizedToken = (CustomizedToken) token;
        String loginName = customizedToken.getUsername();
        AuthInfo authInfo = authenticationService.getUserByLoginName(loginName);
        if(authInfo == null)
            throw new UnknownAccountException();

        String passwordAuth = authInfo.getPassword();
        String salt = authInfo.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authInfo, passwordAuth, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
        /*
        String passwordAuth = authInfo.getPassword();
        String salt = authInfo.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authInfo, passwordAuth, ByteSource.Util.bytes(salt), getName());
        return authenticationInfo;
         */
    }
}

