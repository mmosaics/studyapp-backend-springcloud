package com.hlr.utils;

import com.hlr.adapter.Identifier;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;

public class SessionUtil {


    public static String getUserId(String sessionId) {

        //此处均为获取对象的过程
        RedisManager redisManager = new RedisManager();
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        redisCacheManager.setKeyPrefix("shiro:");
        Cache<String, Object> cache = redisCacheManager.getCache("session");
        SimpleSession authInfo = (SimpleSession) cache.get(sessionId);
        SimplePrincipalCollection test = (SimplePrincipalCollection) authInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);

        //定义了一个适配器，用于获取Id数据
        Identifier identifier = (Identifier)test.getPrimaryPrincipal();

        return identifier.getIdentifier();

    }




}
