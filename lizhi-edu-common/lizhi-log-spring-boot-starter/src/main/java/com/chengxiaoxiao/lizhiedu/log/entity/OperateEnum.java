package com.chengxiaoxiao.lizhiedu.log.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 日志的操作类型
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/10/30
 */
@Getter
@RequiredArgsConstructor
public enum OperateEnum {
    /**
     * 查询
     */
    SELECT(1, "查询"),
    /**
     * 增加
     */
    ADD(2, "增加"),
    /**
     * 修改
     */
    UPDATE(3, "修改"),
    /**
     * 删除
     */
    DELETE(4, "删除"),
    /**
     * 登录
     */
    LOGIN(5, "登录"),
    /**
     * 登出
     */
    LOGOUT(6, "登出"),
    /**
     * 其他
     */
    OTHER(100, "其他");


    /**
     * 操作类型
     */
    private final Integer type;

    /**
     * 描述
     */
    private final String description;
}
