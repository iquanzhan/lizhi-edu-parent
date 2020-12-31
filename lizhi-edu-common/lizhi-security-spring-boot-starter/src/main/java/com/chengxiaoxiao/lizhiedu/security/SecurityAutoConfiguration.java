package com.chengxiaoxiao.lizhiedu.security;

import com.chengxiaoxiao.lizhiedu.security.evaluator.UserPermissionEvaluator;
import com.chengxiaoxiao.lizhiedu.security.provider.UserAuthenticationProvider;
import com.chengxiaoxiao.lizhiedu.security.service.UserLoginService;
import com.chengxiaoxiao.lizhiedu.security.service.impl.UserLoginImpl;
import com.chengxiaoxiao.lizhiedu.security.config.SecurityAuthenticationConfiguration;
import com.chengxiaoxiao.lizhiedu.security.config.SecurityProperties;
import com.chengxiaoxiao.lizhiedu.security.filter.JwtAuthenticationTokenFilter;
import com.chengxiaoxiao.lizhiedu.security.handler.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Security 自动配置类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/3 14:52
 * @Version 1.0
 */
@Configuration
@Import({UserLoginSuccessHandler.class,
        UserLoginFailureHandler.class,
        UserLogoutSuccessHandler.class,
        UserAuthAccessDeniedHandler.class,
        UserLoginDeniedHandler.class,
        UserAuthenticationProvider.class,
        JwtAuthenticationTokenFilter.class,
        UserPermissionEvaluator.class,
        SecurityAuthenticationConfiguration.class
})
@ConditionalOnWebApplication
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public UserLoginService userLoginService() {
        return new UserLoginImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
