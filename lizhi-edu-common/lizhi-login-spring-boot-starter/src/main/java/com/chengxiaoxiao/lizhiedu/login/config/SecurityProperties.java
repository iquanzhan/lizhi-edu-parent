package com.chengxiaoxiao.lizhiedu.login.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * jwt配置类，读取配置文件信息
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/20 10:24 下午
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "lizhi.security")
public class SecurityProperties {

    /**
     * 是否关闭Security
     */
    private boolean enabled = true;
    /**
     * 忽略的路径
     */
    private String ignorePath = "/favicon.ico,/druid/**,/actuator/**,/swagger-ui/**,/swagger-ui,/swagger-resources,/swagger-resources/**,/v3/api-docs,/v2/api-docs,/token/**";

    private JwtConfig jwt = new JwtConfig();

    @Data
    @NoArgsConstructor
    public static class JwtConfig {
        private String secret = "Cheng123456*";
        private String tokenHeader = "TOKEN";
        private String tokenPrefix = "LIZHI-";
        private Integer expiration = 86400;
    }

}
