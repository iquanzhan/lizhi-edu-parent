package com.chengxiaoxiao.lizhiedu.common.core.util;

/**
 * List拷贝的CallBack
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/24 10:33
 * @Version 1.0
 */
@FunctionalInterface
public interface BeanUtilsListCallback<S, T> {
    /**
     * callback
     * @param t
     * @param s
     */
    void callBack(S t, T s);
}
