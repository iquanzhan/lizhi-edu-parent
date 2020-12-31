package com.chengxiaoxiao.lizhiedu.security.handler;


import com.chengxiaoxiao.lizhiedu.common.core.util.ResultUtil;
import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import com.chengxiaoxiao.lizhiedu.security.service.UserLoginService;
import com.chengxiaoxiao.lizhiedu.security.entity.LoginEventModel;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 暂无权限处理类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:37 下午
 * @Description:
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {
    @Resource
    UserLoginService userLoginService;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) {
        userLoginService.accessDenied(new LoginEventModel(request, null, exception));

        ResultUtil.responseJson(response, Result.success(CodeMsg.AUTHENTICATION_ERROR));
    }
}
