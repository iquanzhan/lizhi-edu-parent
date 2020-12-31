package com.chengxiaoxiao.lizhiedu.security.handler;


import com.chengxiaoxiao.lizhiedu.common.core.util.ResultUtil;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import com.chengxiaoxiao.lizhiedu.security.service.UserLoginService;
import com.chengxiaoxiao.lizhiedu.security.entity.LoginEventModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录成功处理类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:38 下午
 * @Description:
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    UserLoginService userLoginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Object result = userLoginService.loginSuccess(new LoginEventModel(request, authentication, null));
        ResultUtil.responseJson(response, Result.success(result));
    }
}