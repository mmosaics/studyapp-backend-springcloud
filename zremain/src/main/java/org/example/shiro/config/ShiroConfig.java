package org.example.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.example.shiro.config.JwtDefaultSubjectFactory;
import org.example.shiro.realm.JwtRealm;
import org.example.shiro.realm.PasswordRealm;
import org.example.shiro.realm.UserModularRealmAuthenticator;
import org.mapstruct.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ShiroConfig {

    /**
     * 不要使用defaultSubject创建对象，这里不需要session
     */
    @Bean
    public SubjectFactory subjectFactory() {
        return new JwtDefaultSubjectFactory();
    }

    @Bean
    public Realm realm() {
        return new JwtRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());

        //关闭shiroDAO
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        //不存储Shiro Session
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        //禁止Subject的getSession方法
        securityManager.setSubjectFactory(subjectFactory());
        return securityManager;

    }

    /**
     * shiro aop注解支持
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 密码适配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();

        matcher.setHashAlgorithmName("SHA-256");
        matcher.setHashIterations(2);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

    @Bean
    public PasswordRealm passwordRealm(HashedCredentialsMatcher matcher) {
        PasswordRealm passwordRealm = new PasswordRealm();
        passwordRealm.setCredentialsMatcher(matcher);
        return passwordRealm;
    }

    @Bean
    public JwtRealm jwtRealm() {
        return new JwtRealm();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    @Bean
    public UserModularRealmAuthenticator userModularRealmAuthenticator() {
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }


    /**
     * SecurityManager是Shiro架构的核心，链接Realm和Subject
     * @param passwordRealm
     * @param jwtRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(PasswordRealm passwordRealm, JwtRealm jwtRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setAuthenticator(userModularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();

        //添加realm
        realms.add(passwordRealm);
        realms.add(jwtRealm);

        securityManager.setRealms(realms);

        /**
         * 关闭shiro的session
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }


}
