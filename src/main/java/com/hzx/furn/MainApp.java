package com.hzx.furn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jools He
 * @version 1.0
 * @date 2024/5/5 11:56
 * @description: TODO
 */
@MapperScan(basePackages = "com.hzx.furn.mapper")
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {

        SpringApplication.run(MainApp.class, args);
    }
}
