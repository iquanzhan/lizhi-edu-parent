package com.chengxiaoxiao.lizhiedu.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * WebMvc配置
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/2 15:47
 * @Version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        UserArgumentResolver userArgumentResolver = new UserArgumentResolver();
        resolvers.add(userArgumentResolver);
    }
}
