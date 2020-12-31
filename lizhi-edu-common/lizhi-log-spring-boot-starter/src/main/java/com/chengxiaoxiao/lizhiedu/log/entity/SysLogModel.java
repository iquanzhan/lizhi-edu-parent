package com.chengxiaoxiao.lizhiedu.log.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志对象
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLogModel implements Serializable {

    private static final long serialVersionUID = 5858129908585988910L;

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 日志操作类型
     */
    private Integer operateType;

    /**
     * 模块名称
     */
    private String moduleName;


    /**
     * 日志描述
     */
    private String title;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 操作IP地址
     */
    private String ip;


    /**
     * 用户浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String operateSystem;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 请求URI
     */
    private String requestUri;

    /**
     * 操作方式
     */
    private String method;

    /**
     * 操作提交的数据
     */
    private String params;


    /**
     * 执行时间
     */
    private Long time;

    /**
     * 异常信息
     */
    private String exception;

}
