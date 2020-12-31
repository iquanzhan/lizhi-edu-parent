package com.chengxiaoxiao.lizhiedu.common.core.util;

import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * List集合的整体拷贝
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/24 10:29
 * @Version 1.0
 */
public class LizhiBeanUtil extends BeanUtil {
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }


    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanUtilsListCallback<S, T> callback) {
        List<T> list = new ArrayList<>();
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            if (callback != null) {
                callback.callBack(source, t);
            }
            list.add(t);
        }
        return list;
    }
}
