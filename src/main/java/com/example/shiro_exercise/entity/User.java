package com.example.shiro_exercise.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.shiro_exercise.dto.UserDTO;
import lombok.Data;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-03-06 15:42:19
 */
@Data
public class User extends Model<User> {

    private Integer id;

    private String name;

    private String password;

    private String salt;

    private String role;

    public User(UserDTO userDTO) {
        this.name = userDTO.getName();
        this.password = userDTO.getPassword();
    }

    public User() {
    }
}

