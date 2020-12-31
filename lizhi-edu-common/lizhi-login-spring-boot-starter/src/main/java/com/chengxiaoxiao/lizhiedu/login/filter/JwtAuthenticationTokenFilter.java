package com.chengxiaoxiao.lizhiedu.login.filter;

import com.chengxiaoxiao.lizhiedu.common.core.util.ResultUtil;
import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import com.chengxiaoxiao.lizhiedu.login.config.SecurityProperties;
import com.chengxiaoxiao.lizhiedu.login.service.UserTokenService;
import com.chengxiaoxiao.lizhiedu.login.util.AppThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
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
 * @CreateTime 2020/12/02 16:41
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private UserTokenService userTokenService;
    @Resource
    private SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取排除的路径
        boolean isIgnore = isIgnorePath(request);
        if (isIgnore) {
            filterChain.doFilter(request, response);
            return;
        }

        //获取用户数据
        String tokenHeader = securityProperties.getJwt().getTokenHeader();
        try {
            String token = request.getHeader(tokenHeader);
            if (token == null || token.length() <= 0) {
                ResultUtil.responseJson(response, Result.error(CodeMsg.USER_NOT_LOGIN_ERROR));
                return;
            }
            Result<LoginUser> userInfoByToken = userTokenService.getUserInfoByToken(token);
            if ("0".equals(userInfoByToken.getCode())) {
                AppThreadLocalUtils.setUser(userInfoByToken.getData());
                filterChain.doFilter(request, response);
            } else {
                //返回错误信息
                ResultUtil.responseJson(response, Result.error(new CodeMsg(userInfoByToken.getCode(), userInfoByToken.getMsg())));
            }
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            ResultUtil.responseJson(response, Result.error(CodeMsg.OTHER_ERROR.fillArgs(exception.getMessage())));
        }
    }

    /**
     * 判断是否忽略路径校验
     *
     * @param request 请求
     * @return
     */
    private boolean isIgnorePath(HttpServletRequest request) {
        String[] ignorePath = securityProperties.getIgnorePath().split(",");
        boolean isIgnorePath = false;

        AntPathMatcher matcher = new AntPathMatcher();
        String requestURI = request.getRequestURI();
        for (String pattern : ignorePath) {
            if (matcher.match(pattern, requestURI)) {
                isIgnorePath = true;
                break;
            }
        }
        return isIgnorePath;
    }

}