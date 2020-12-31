package com.chengxiaoxiao.lizhiedu.log.service;

import com.chengxiaoxiao.lizhiedu.log.entity.SysLogModel;

/**
 * 日志模块业务逻辑层
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/30
 */
public interface SysLogOperateService {

    /**
     * 写入日志信息
     *
     * @param source 日志事件
     */
    void writeLog(SysLogModel source);
}
