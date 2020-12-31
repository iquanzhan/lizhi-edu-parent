package com.chengxiaoxiao.lizhiedu.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 代码生成器-启动器
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/12 16:49
 * @Version 1.0
 */
@SpringBootApplication
public class CodeGeneratorApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CodeGeneratorApplication.class);
    }
}
