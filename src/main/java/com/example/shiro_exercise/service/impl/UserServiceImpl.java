package com.example.shiro_exercise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shiro_exercise.entity.User;
import com.example.shiro_exercise.mapper.UserMapper;
import com.example.shiro_exercise.service.UserService;
import com.example.shiro_exercise.configuration.SaltUtil;
import com.example.shiro_exercise.configuration.ShiroConstant;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-03-05 20:52:32
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        // 生成随机盐
        String salt = SaltUtil.getSalt(ShiroConstant.SALT_LENGTH);
        // 保存随机盐
        user.setSalt(salt);
        // 生成密码
        Md5Hash password = new Md5Hash(user.getPassword(),salt,ShiroConstant.HASH_ITERATORS);
        // 保存密码
        user.setPassword(password.toHex());
        user.setRole("0");
        userMapper.insert(user);
    }

    @Override
    public User findUser(String name){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        User user = userMapper.selectOne(wrapper);
        return user;
    }
}

