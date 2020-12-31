package com.chengxiaoxiao.lizhiedu.log.event;


import com.chengxiaoxiao.lizhiedu.log.entity.SysLogModel;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/29
 */
public class SysLogEvent extends ApplicationEvent {
	/**
	 * 定义系统日志事件
	 * @param source 事件源
	 */
	public SysLogEvent(SysLogModel source) {
		super(source);
	}
}
