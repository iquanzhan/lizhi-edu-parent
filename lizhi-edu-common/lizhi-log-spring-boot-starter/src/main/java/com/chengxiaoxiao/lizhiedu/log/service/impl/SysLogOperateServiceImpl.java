package com.chengxiaoxiao.lizhiedu.log.service.impl;

import com.chengxiaoxiao.lizhiedu.log.entity.SysLogModel;
import com.chengxiaoxiao.lizhiedu.log.service.SysLogOperateService;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统日志信息实现类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/3 13:24
 * @Version 1.0
 */
@Slf4j
public class SysLogOperateServiceImpl implements SysLogOperateService {
    @Override
    public void writeLog(SysLogModel source) {
        if (log.isDebugEnabled()) {
            log.debug("日志详情：{}", source);
        }
    }
}
