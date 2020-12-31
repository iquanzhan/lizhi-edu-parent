package com.chengxiaoxiao.lizhiedu.log.aspect;

import cn.hutool.core.util.StrUtil;
import com.chengxiaoxiao.lizhiedu.common.core.util.SpringContextHolderUtil;
import com.chengxiaoxiao.lizhiedu.log.event.SysLogEvent;
import com.chengxiaoxiao.lizhiedu.log.util.SysLogUtils;
import com.chengxiaoxiao.lizhiedu.log.annotation.SysLog;
import com.chengxiaoxiao.lizhiedu.log.entity.LogTypeEnum;
import com.chengxiaoxiao.lizhiedu.log.entity.LoggingProperties;
import com.chengxiaoxiao.lizhiedu.log.entity.SysLogModel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
public class SysLogAspect {

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private LoggingProperties loggingProperties;

    @Around("@annotation(sysLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, SysLog sysLog) {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();

        if (log.isDebugEnabled()) {
            log.debug("[模块名]：{}，[类名]：{}，[方法]：{}", applicationName, className, methodName);
        }

        SysLogModel logVo = SysLogUtils.getSysLog(sysLog, applicationName);

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

            if (sysLog.isStore()) {
                SpringContextHolderUtil.publishEvent(new SysLogEvent(logVo));
            }
        }

        return obj;
    }


    /**
     * 打印日志信息
     *
     * @param logVo 日志对象
     */
    private void printfLog(SysLogModel logVo) {
        if (loggingProperties.getHttpLog().isEnabled()) {
            //打印操作日志
            log.info("===============================[{}]:{}-start===================================", applicationName, logVo.getModuleName(), logVo.getTitle());
            log.info("{} {}", logVo.getMethod(), logVo.getRequestUri());
            log.info("agent：{}", logVo.getUserAgent());
            log.info("params：{}", logVo.getParams());
            if (!StrUtil.isBlank(logVo.getException())) {
                log.info("exception：{}", logVo.getException());
            }
            log.info("time：{}", logVo.getTime());

            log.info("===============================[{}]:{}-end===================================", applicationName, logVo.getModuleName(), logVo.getTitle());
        }
    }
}
