package com.chengxiaoxiao.lizhiedu.security.handler;


import com.chengxiaoxiao.lizhiedu.common.core.util.ResultUtil;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import com.chengxiaoxiao.lizhiedu.security.service.UserLoginService;
import com.chengxiaoxiao.lizhiedu.security.entity.LoginEventModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登出类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:39 下午
 * @Description:
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    UserLoginService userLoginService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        userLoginService.logoutSuccess(new LoginEventModel(request, authentication, null));

        SecurityContextHolder.clearContext();
        ResultUtil.responseJson(response, Result.success("注销成功"));
    }
}
