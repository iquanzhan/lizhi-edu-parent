package com.chengxiaoxiao.lizhiedu.security.filter;

import com.chengxiaoxiao.lizhiedu.security.entity.AuthenticationBean;
import com.chengxiaoxiao.lizhiedu.security.entity.constant.SecurityConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义用户名密码拦截器，实现json方式提交用户名密码
 *
 * @Author: Cheng XiaoXiao
 * @Date: 2020/2/21 10:58 下午
 * @Description:
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (request.getContentType().toLowerCase().equals(MediaType.APPLICATION_JSON_VALUE.toLowerCase()) ||
                request.getContentType().toLowerCase().equals(MediaType.APPLICATION_JSON_UTF8_VALUE.toLowerCase())) {
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authRequest = null;
            try (InputStream is = request.getInputStream()) {
                AuthenticationBean authenticationBean = mapper.readValue(is, AuthenticationBean.class);
                request.setAttribute(SecurityConstant.LOGIN_USER_NAME, authenticationBean.getUserName());

                authRequest = new UsernamePasswordAuthenticationToken(
                        authenticationBean.getUserName(), authenticationBean.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken(
                        "", "");
            } finally {
                setDetails(request, authRequest);
            }
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}
