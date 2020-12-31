package com.chengxiaoxiao.lizhiedu.log;


import com.chengxiaoxiao.lizhiedu.log.aspect.SysLogAspect;
import com.chengxiaoxiao.lizhiedu.log.event.SysLogListener;
import com.chengxiaoxiao.lizhiedu.log.aspect.HttpLogAspect;
import com.chengxiaoxiao.lizhiedu.log.entity.LoggingProperties;
import com.chengxiaoxiao.lizhiedu.log.service.SysLogOperateService;
import com.chengxiaoxiao.lizhiedu.log.service.impl.SysLogOperateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志模块自动配置类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/30
 */
@EnableAsync
@RequiredArgsConstructor
@ConditionalOnWebApplication
@EnableConfigurationProperties(LoggingProperties.class)
@Configuration(proxyBeanMethods = false)
public class LogAutoConfiguration {

    @Bean
    public SysLogListener sysLogListener() {
        return new SysLogListener();
    }

    @Bean
    @ConditionalOnProperty(name = "lizhi.logging.sysLog.enabled", matchIfMissing = true)
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }

    @Bean
    @ConditionalOnProperty(name = "lizhi.logging.httpLog.enabled", matchIfMissing = true)
    public HttpLogAspect httpLogAspect() {
        return new HttpLogAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public SysLogOperateService sysLogOperateService() {
        return new SysLogOperateServiceImpl();
    }

}
