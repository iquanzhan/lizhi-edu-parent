package com.chengxiaoxiao.lizhiedu.security.service;

import com.chengxiaoxiao.lizhiedu.security.entity.LoginEventModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录处理类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/3 13:42
 * @Version 1.0
 */
public interface UserLoginService {

    /**
     * 用户没有权限调用
     *
     * @param model 事件信息
     */
    void accessDenied(LoginEventModel model);

    /**
     * 用户没有登录调用
     *
     * @param model 事件信息
     */
    void loginDenied(LoginEventModel model);

    /**
     * 用户登录失败调用
     *
     * @param model 事件信息
     */
    void loginFailure(LoginEventModel model);

    /**
     * 用户登录成功调用
     *
     * @param model 认证对象
     * @return 接口返回值，一般应为token
     */
    Object loginSuccess(LoginEventModel model);

    /**
     * 用户注销成功调用
     *
     * @param model 事件信息
     */
    void logoutSuccess(LoginEventModel model);

    /**
     * 处理用户登录
     *
     * @param authentication 验证信息，用户输入的用户名密码信息
     * @return Spring Security所需要的UserDetails
     */
    UserDetails handleLogin(Authentication authentication);

    /**
     * 过滤校验TOKEN是否有效
     *
     * @param request 请求信息
     */
    void filterCheckToken(HttpServletRequest request);
}
