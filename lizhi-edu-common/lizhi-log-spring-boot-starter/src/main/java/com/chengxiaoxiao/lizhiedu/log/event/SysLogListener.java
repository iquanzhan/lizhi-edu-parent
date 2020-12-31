package com.chengxiaoxiao.lizhiedu.log.event;


import com.chengxiaoxiao.lizhiedu.log.entity.SysLogModel;
import com.chengxiaoxiao.lizhiedu.log.service.SysLogOperateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;

/**
 * 异步监听事件
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/29
 */
@Slf4j
@RequiredArgsConstructor
public class SysLogListener {

    @Resource
    SysLogOperateService sysLogOperateService;

    /**
     * 异步事件监听
     * @param event 事件信息
     */
    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLogModel sysLogModel = (SysLogModel) event.getSource();
        sysLogOperateService.writeLog(sysLogModel);
    }

}
