package com.chengxiaoxiao.lizhiedu.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 文件服务启动器
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/12/03
 */
@Slf4j
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FileApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
        log.info("文件服务启动成功...");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FileApplication.class);
    }
}
