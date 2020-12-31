package com.chengxiaoxiao.lizhiedu.auth;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 鉴权服务启动器
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/28
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.chengxiaoxiao.lizhiedu.auth.mapper")
public class AuthApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
        log.info("用户鉴权中心服务启动成功...");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AuthApplication.class);
    }
}
