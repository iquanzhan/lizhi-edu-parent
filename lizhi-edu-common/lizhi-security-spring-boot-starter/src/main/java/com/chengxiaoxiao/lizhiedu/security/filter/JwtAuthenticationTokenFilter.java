package com.chengxiaoxiao.lizhiedu.security.filter;

import com.chengxiaoxiao.lizhiedu.security.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT接口请求校验拦截器
 * 请求接口时会进入这里验证Token是否合法和过期
 *
 * @Author Cheng xiaoxiao
 * @CreateTime 2020/6/1 16:41
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    UserLoginService userLoginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        userLoginService.filterCheckToken(request);

        filterChain.doFilter(request, response);
        return;
    }

}