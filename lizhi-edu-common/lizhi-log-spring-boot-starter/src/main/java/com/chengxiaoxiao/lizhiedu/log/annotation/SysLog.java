package com.chengxiaoxiao.lizhiedu.log.annotation;

import com.chengxiaoxiao.lizhiedu.log.entity.OperateEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解
 *
 * @Description: 操作日志注解，用以标识到controller方法上
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {
    /**
     * 日志操作类型
     *
     * @return {OperateEnum}
     */
    OperateEnum operateType() default OperateEnum.OTHER;

    /**
     * 模块名称
     *
     * @return {String}
     */
    String moduleName() default "默认模块";

    /**
     * 描述信息
     *
     * @return {String}
     */
    String value();

    /**
     * 是否存储记录,默认存储
     *
     * @return {boolean}
     */
    boolean isStore() default true;
}
