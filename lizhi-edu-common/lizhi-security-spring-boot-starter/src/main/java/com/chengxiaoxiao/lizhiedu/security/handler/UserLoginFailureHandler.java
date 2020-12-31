package com.chengxiaoxiao.lizhiedu.security.handler;


import com.chengxiaoxiao.lizhiedu.common.core.util.ResultUtil;
import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import com.chengxiaoxiao.lizhiedu.security.entity.LoginEventModel;
import com.chengxiaoxiao.lizhiedu.security.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录失败处理类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:38 下午
 * @Description:
 */
@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Resource
    UserLoginService userLoginService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {

        // 这些对于操作的处理类可以根据不同异常进行不同处理
        Result result = Result.error(CodeMsg.ERROR);

        if (exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】" + exception.getMessage());
            result = Result.error(CodeMsg.USER_PASSWORD_INCORRENT);
        }
        if (exception instanceof LockedException) {
            log.info("【登录失败】" + exception.getMessage());
            result = Result.error(CodeMsg.USER_LOCKED);
        }
        if (exception instanceof BadCredentialsException) {
            log.info("【登录失败】" + exception.getMessage());
            result = Result.error(CodeMsg.USER_PASSWORD_INCORRENT);
        }

        userLoginService.loginFailure(new LoginEventModel(request, null, exception));

        ResultUtil.responseJson(response, result);
    }
}
