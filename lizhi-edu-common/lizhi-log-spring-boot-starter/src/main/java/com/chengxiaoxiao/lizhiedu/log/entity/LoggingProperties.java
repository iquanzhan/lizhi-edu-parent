package com.chengxiaoxiao.lizhiedu.log.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志属性类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/2 11:01
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "lizhi.logging")
public class LoggingProperties {
    /**
     * 系统操作日志的相关设置
     */
    private SysLog sysLog = new SysLog();

    @Data
    public static class SysLog {
        /**
         * 是否开启操作日志输出
         */
        private boolean enabled = true;
    }


    /**
     * HTTP请求日志的相关设置
     */
    private HttpLog httpLog = new HttpLog();

    @Data
    public static class HttpLog {
        /**
         * 是否开启HTTP请求日志输出
         */
        private boolean enabled = true;
    }
}
