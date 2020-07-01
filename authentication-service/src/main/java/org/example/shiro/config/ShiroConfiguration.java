package org.example.shiro.config;

import lombok.Data;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.example.shiro.matcher.RetryLimitCredentialsMatcher;
import org.example.shiro.realm.JPARealm;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class ShiroConfiguration {

    /**
     * 均为redis的配置变量
     */
    //@Value("${shiro.redis.host:}")
    private String host = "localhost";

    //@Value("${shiro.redis.port:}")
    private int port = 6379;

    private String password;
    private Duration timeout;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 此处为过滤器的配置
     * 先保留，后面再实现
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager
     * 权限管理，配置Realm
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getJPARealm());
        //自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        //自定义缓存实现
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * 添加凭证匹配起到Realm当中
     * @return JPARealm
     */
    @Bean
    public JPARealm getJPARealm() {
        JPARealm jpaRealm = new JPARealm();
        jpaRealm.setCredentialsMatcher(retryLimitCredentialsMatcher());
        return jpaRealm;
    }

    /**
     * 凭证匹配器 需要交给Shiro的SimpleAuthenticationInfo处理
     * 说明：此处使用md5散列用户的密码，并且进行了两次散列
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public RetryLimitCredentialsMatcher retryLimitCredentialsMatcher() {
        RetryLimitCredentialsMatcher retryLimitCredentialsMatcher = new RetryLimitCredentialsMatcher(retryLimitCacheManager());
        retryLimitCredentialsMatcher.setHashAlgorithmName("SHA-256");
        retryLimitCredentialsMatcher.setHashIterations(2);
        return retryLimitCredentialsMatcher;

    }

    /**
     * redis通信的DAO
     * 此处是需要shiro-redis的依赖，实现对redis的访问
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        redisSessionDAO.setExpire(1800);
        return redisSessionDAO;

    }

    /**
     * Session ID 生成器
     * @return JavaUuidSessionIdGenerator
     */
    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 会话管理器
     * @return SessionManager
     */
    @Bean
    public SessionManager sessionManager() {
        UserSessionManager userSessionManager = new UserSessionManager();
        userSessionManager.setSessionDAO(redisSessionDAO());
        return userSessionManager;
    }

    /**
     * RedisManager
     * 用于配置redis本身的相关信息
     * @return RedisManager
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        //redisManager.setTimeout((int) timeout.toMillis());
        redisManager.setPassword(password);
        return redisManager;
    }


    /**
     * RedisCacheManager
     * 用于配置具体数据的信息
     * @return RedisCacheManager
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //主键名称
        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    /**
     * 用于限制登录次数的manager
     * @return
     */
    @Bean
    public RedisCacheManager retryLimitCacheManager() {

        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());

        int oneDay = 12*60*60;
        redisCacheManager.setExpire(oneDay);

        return redisCacheManager;

    }


    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * 暂时应该用不到，AOP的东西
     * @return DefaultAdvisorAutoProxyCreator
     */
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 同上
     * @param securityManager
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleCookie cookie() {
        SimpleCookie cookie = new SimpleCookie("SHARE_JSESSIONID");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }


}

