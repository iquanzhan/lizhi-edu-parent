package com.chengxiaoxiao.lizhiedu.database.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Druid连接池的相关配置
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/5 10:51
 * @Version 1.0
 */
@Data
@ConfigurationProperties("lizhi.database.druid")
public class DruidProperties {
    /**
     * 登录用户名
     */
    private String userName = "admin";
    /**
     * 登录密码
     */
    private String password = "admin";
}
