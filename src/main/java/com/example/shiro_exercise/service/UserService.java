package com.example.shiro_exercise.service;

import com.example.shiro_exercise.entity.User;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-03-05 20:52:32
 */
public interface UserService  {

    void register(User user);

    User findUser(String name);
}

