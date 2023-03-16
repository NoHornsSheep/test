package com.example.shiro_exercise;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author NoHornsSheep
 */
@SpringBootApplication
@MapperScan("com.example.shiro_exercise.mapper")
public class ShiroExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroExerciseApplication.class, args);
    }

}
