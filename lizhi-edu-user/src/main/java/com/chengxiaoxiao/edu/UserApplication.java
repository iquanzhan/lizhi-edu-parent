package com.chengxiaoxiao.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/3/29 8:26 下午
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.chengxiaoxiao.edu.mapper")
@EnableEurekaClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
