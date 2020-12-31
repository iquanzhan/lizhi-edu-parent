package com.chengxiaoxiao.lizhiedu.dto.constant;

/**
 * Redis Key统一管理
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/18 9:54
 */
public class RedisKeyConstant {

    /**
     * 应用总体前缀
     */
    private static final String PREFIX = "chengxiaoxiao:lizhi-edu_";


    /***************************************************用户权限中心*******************************************************/
    /**
     * 验证中心基础路径
     */
    private static final String MODULE_AUTH_BASE_KEY = PREFIX + "auth-center_";
    /**
     * 用户TOKEN KEY
     */
    public static final String USER_TOKEN = MODULE_AUTH_BASE_KEY + "token:%s";

    /***************************************************用户权限中心*******************************************************/
}
