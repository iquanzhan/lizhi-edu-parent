package com.chengxiaoxiao.lizhiedu.security.service.impl;

import com.chengxiaoxiao.lizhiedu.security.service.UserLoginService;
import com.chengxiaoxiao.lizhiedu.security.entity.LoginEventModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录默认的业务逻辑层
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/3 13:52
 * @Version 1.0
 */
public class UserLoginImpl implements UserLoginService {
    @Override
    public void accessDenied(LoginEventModel model) {

    }

    @Override
    public void loginDenied(LoginEventModel model) {

    }

    @Override
    public void loginFailure(LoginEventModel model) {

    }

    @Override
    public Object loginSuccess(LoginEventModel model) {
        return null;
    }

    @Override
    public void logoutSuccess(LoginEventModel model) {

    }

    @Override
    public UserDetails handleLogin(Authentication authentication) {
        return null;
    }

    @Override
    public void filterCheckToken(HttpServletRequest request) {

    }
}
