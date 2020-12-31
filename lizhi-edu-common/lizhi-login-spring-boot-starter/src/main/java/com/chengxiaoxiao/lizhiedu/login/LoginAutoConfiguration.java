package com.chengxiaoxiao.lizhiedu.login;

import com.chengxiaoxiao.lizhiedu.login.config.SecurityProperties;
import com.chengxiaoxiao.lizhiedu.login.config.WebMvcConfig;
import com.chengxiaoxiao.lizhiedu.login.filter.JwtAuthenticationTokenFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 登录用户自动配置类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/2 14:11
 * @Version 1.0
 */
@Configuration
@Import({WebMvcConfig.class, JwtAuthenticationTokenFilter.class})
@EnableConfigurationProperties(SecurityProperties.class)
public class LoginAutoConfiguration {
}
