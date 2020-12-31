package com.chengxiaoxiao.lizhiedu.log.aspect;

import cn.hutool.core.util.StrUtil;
import com.chengxiaoxiao.lizhiedu.log.entity.SysLogModel;
import com.chengxiaoxiao.lizhiedu.log.util.SysLogUtils;
import com.chengxiaoxiao.lizhiedu.log.entity.LogTypeEnum;
import com.chengxiaoxiao.lizhiedu.log.entity.LoggingProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * 操作日志的切面
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/29
 */
@Aspect
@Slf4j
public class HttpLogAspect {

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private LoggingProperties loggingProperties;


    @Pointcut("execution(public * com.chengxiaoxiao..*.controller.*Controller.*(..))")
    public void log() {
    }


    @Around("log()")
    @SneakyThrows
    public Object beforeLog(ProceedingJoinPoint point) {
        SysLogModel logVo = SysLogUtils.getHttpLog(applicationName);

        Long startTime = System.currentTimeMillis();
        Object obj;

        try {
            obj = point.proceed();
        } catch (Exception e) {
            logVo.setType(LogTypeEnum.ERROR.getType());
            logVo.setException(e.getMessage());
            throw e;
        } finally {
            Long endTime = System.currentTimeMillis();
            logVo.setTime(endTime - startTime);

            printfLog(logVo);
        }

        return obj;
    }


    /**
     * 打印日志信息
     *
     * @param logVo 日志对象
     */
    private void printfLog(SysLogModel logVo) {
        if (!loggingProperties.getSysLog().isEnabled()) {
            //打印操作日志
            log.info("==========Application:[{}]===[{}]-start==================", applicationName, logVo.getRequestUri());
            log.info("{} {}", logVo.getMethod(), logVo.getRequestUri());
            log.info("agent：{}", logVo.getUserAgent());
            log.info("params：{}", logVo.getParams());
            if (!StrUtil.isBlank(logVo.getException())) {
                log.info("exception：{}", logVo.getException());
            }
            log.info("time：{}", logVo.getTime());

            log.info("==========Application:[{}]===[{}]-end====================", applicationName, logVo.getRequestUri());
        }
    }
}
