package com.chengxiaoxiao.lizhiedu.auth.handler;

import cn.hutool.core.bean.BeanUtil;
import com.chengxiaoxiao.lizhiedu.auth.entity.SecurityUser;
import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * 用户类型解析器
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/3/24 10:35
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        return parameterType == LoginUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginUser loginUser = new LoginUser();

        BeanUtil.copyProperties(securityUser.getCurrentUserInfoInfo(), loginUser);

        return loginUser;
    }
}
