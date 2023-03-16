package com.example.shiro_exercise.configuration;

import com.example.shiro_exercise.entity.User;
import com.example.shiro_exercise.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author NoHornsSheep
 */


public class UserRealm extends AuthorizingRealm {

    @Autowired
    public UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取主身份信息
        String principal = (String) principals.getPrimaryPrincipal();
        // 根据主身份信息获取角色信息
        User user = userService.findUser(principal);
        if (user != null) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole(user.getRole());
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 在token中获取用户名
        String principal = (String) token.getPrincipal();
        // 模拟根据身份信息从数据库查询
        User user = userService.findUser(principal);

        if (user != null){
            return new SimpleAuthenticationInfo(user.getName(),user.getPassword(), new UserByteSource(user.getSalt()),this.getName());
        }
        return null;
    }
}
