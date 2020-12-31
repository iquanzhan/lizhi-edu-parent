package com.chengxiaoxiao.lizhiedu.common.core.util;

import cn.hutool.core.util.StrUtil;

/**
 * 字符串工具类扩展
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/5 10:04
 * @Version 1.0
 */
public class StringUtil {

    /**
     * 字符串转换为Boolean值
     *
     * @param str 字符串
     * @return 转换后的Boolean
     */
    public static Boolean convertToBoolean(String str) {
        return Boolean.parseBoolean(str);
    }


    /**
     * 字符串转换为Boolean值，可以设置默认值
     *
     * @param str        字符串
     * @param defaultVal 默认值
     * @return Boolean
     */
    public static Boolean convertToBoolean(String str, Boolean defaultVal) {
        if (StrUtil.isBlank(str)) {
            return defaultVal;
        }
        return convertToBoolean(str);
    }
}
