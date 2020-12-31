package com.chengxiaoxiao.lizhiedu.code.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 设置虚拟路径访问绝对路径下的资源
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/7/3 17:31
 */
@Configuration
public class UploadFilePathConfig implements WebMvcConfigurer {

    @Autowired
    private FileProperties fileProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("file:" + StrUtil.appendIfMissing(fileProperties.getUploadPath(),"/"));
    }
}
