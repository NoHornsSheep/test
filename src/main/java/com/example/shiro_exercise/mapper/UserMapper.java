package com.example.shiro_exercise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shiro_exercise.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-05 20:52:31
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

