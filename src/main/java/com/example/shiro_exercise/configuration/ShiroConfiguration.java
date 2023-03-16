package com.example.shiro_exercise.configuration;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Shiro的核心配置类，用来整合shiro框架
 */
@Configuration
public class ShiroConfiguration {

    //1.创建shiroFilter  //负责拦截所有请求
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统受限资源
        //配置系统公共资源
        Map<String,String> map = new HashMap<String,String>();
        map.put("/user/login","anon");  // anon 设置为公共资源，放行要注意anon和authc的顺序
        map.put("/user/register","anon");
        map.put("/index","authc");      //authc 请求这个资源需要认证和授权


        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm(){
        UserRealm customerRealm = new UserRealm();
        // 设置密码匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密方式
        credentialsMatcher.setHashAlgorithmName(ShiroConstant.HASH_ALGORITHM_NAME.MD5);
        // 设置散列次数
        credentialsMatcher.setHashIterations(ShiroConstant.HASH_ITERATORS);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }
}
