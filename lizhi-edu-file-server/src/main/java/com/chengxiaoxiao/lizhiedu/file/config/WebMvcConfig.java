package com.chengxiaoxiao.lizhiedu.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 9:55 下午
 * @Description:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private FileProperties fileProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + fileProperties.getSavePath());
    }

}
