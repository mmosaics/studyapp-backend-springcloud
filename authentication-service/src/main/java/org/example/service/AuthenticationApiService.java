package org.example.service;

import com.hlr.utils.SessionUtil;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shiro.cache.Cache;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationApiService implements AuthService{

    @Override
    public boolean authUser(String sessionId) {

        RedisManager redisManager = new RedisManager();
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        redisCacheManager.setKeyPrefix("shiro:");
        Cache<String, Object> cache = redisCacheManager.getCache("session");
        Object res = cache.get(sessionId);

        if(res == null)
            return false;
        else
            return true;

    }

    @Override
    public Map<String, Object> authUserInfo(String sessionId) {

        Map<String, Object> map = new HashMap<>();

        String userId = SessionUtil.getUserId(sessionId);

        map.put("userId", userId);

        return map;

    }
}
