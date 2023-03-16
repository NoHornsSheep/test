package com.example.shiro_exercise.controller;


import com.example.shiro_exercise.dto.UserDTO;
import com.example.shiro_exercise.entity.User;
import com.example.shiro_exercise.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2023-03-05 20:52:30
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public String register(@RequestBody UserDTO userDTO){
        try {
            User user = new User(userDTO);
            userService.register(user);
            return "login";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "register";
    }

    @PostMapping("login")
    public String login(@RequestBody UserDTO userDTO){
        // 获取当前登录用户
        Subject subject = SecurityUtils.getSubject();

        try {
            // 执行登录操作
            subject.login(new UsernamePasswordToken(userDTO.getName(),userDTO.getPassword()));
            // 认证通过后直接跳转到index.jsp
            return "/index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误~");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误~");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 如果认证失败仍然回到登录页面
        return "/login";
    }

    @PostMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        // 退出后仍然会到登录页面
        return "/login";
    }

    @PostMapping("hello")
    public String sayHello(){
        return "hello";
    }
}

