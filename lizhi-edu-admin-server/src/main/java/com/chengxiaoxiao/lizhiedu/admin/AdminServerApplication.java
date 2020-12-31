package com.chengxiaoxiao.lizhiedu.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Sprinng Admin Server启动器
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/10/31 10:07
 * @Version 1.0
 */
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class AdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }
}
