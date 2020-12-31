package com.chengxiaoxiao.lizhiedu.login.config;

import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import com.chengxiaoxiao.lizhiedu.login.util.AppThreadLocalUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * 用户类型解析器
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/12/02 14:28
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        return parameterType == LoginUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return AppThreadLocalUtils.getUser();
    }
}
