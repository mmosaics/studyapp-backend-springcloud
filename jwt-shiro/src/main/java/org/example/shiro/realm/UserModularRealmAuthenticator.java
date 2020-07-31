package org.example.shiro.realm;


import io.jsonwebtoken.Jwt;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.example.shiro.token.CustomizedToken;
import org.example.shiro.token.JwtToken;

import java.util.ArrayList;
import java.util.Collection;

public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();

        // all realm
        Collection<Realm> realms = getRealms();
        // 登录类型对应的所有Realm
        Collection<Realm> typeRealms = new ArrayList<>();
        // 强制转换为自定义的Token
        try {
            JwtToken jwtToken = (JwtToken) authenticationToken;
            for(Realm realm : realms) {
                if(realm.getName().contains("Jwt")) {
                    typeRealms.add(realm);
                }
            }
            return doSingleRealmAuthentication(typeRealms.iterator().next(), jwtToken);
        } catch (ClassCastException e) {
            typeRealms.clear();
            CustomizedToken customizedToken = (CustomizedToken) authenticationToken;
            //登录类型
            String loginType = customizedToken.getLoginType();
            for(Realm realm: realms) {
                if(realm.getName().contains(loginType)) {
                    typeRealms.add(realm);
                }
            }

            if(typeRealms.size() == 1) {
                return doSingleRealmAuthentication(typeRealms.iterator().next(), customizedToken);
            } else {
                return doMultiRealmAuthentication(typeRealms, customizedToken);
            }
        }
    }
}
