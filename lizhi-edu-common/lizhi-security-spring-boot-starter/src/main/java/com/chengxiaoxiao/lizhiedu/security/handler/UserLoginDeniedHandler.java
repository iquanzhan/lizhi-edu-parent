package com.chengxiaoxiao.lizhiedu.security.handler;


import com.chengxiaoxiao.lizhiedu.common.core.util.ResultUtil;
import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import com.chengxiaoxiao.lizhiedu.security.service.UserLoginService;
import com.chengxiaoxiao.lizhiedu.security.entity.LoginEventModel;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户未登录处理类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:37 下午
 * @Description:
 */
@Component
public class UserLoginDeniedHandler implements AuthenticationEntryPoint {

    @Resource
    UserLoginService userLoginService;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        userLoginService.loginDenied(new LoginEventModel(request, null, exception));

        ResultUtil.responseJson(response, Result.success(CodeMsg.USER_NOT_LOGIN_ERROR));
    }
}
