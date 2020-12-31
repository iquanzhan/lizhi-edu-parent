package com.chengxiaoxiao.lizhiedu.login.util;


import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;


/**
 * 登录用户工具类
 *
 * @Author Cheng xiaoxiao
 * @CreateTime 2020/12/02 16:41
 */
public class AppThreadLocalUtils {

    private final static ThreadLocal<LoginUser> userThreadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程中的用户
     *
     * @param user
     */
    public static void setUser(LoginUser user) {
        userThreadLocal.set(user);
    }

    /**
     * 获取线程中的用户
     *
     * @return
     */
    public static LoginUser getUser() {
        return userThreadLocal.get();
    }

}
