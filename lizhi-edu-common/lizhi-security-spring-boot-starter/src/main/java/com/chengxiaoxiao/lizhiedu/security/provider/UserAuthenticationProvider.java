package com.chengxiaoxiao.lizhiedu.security.provider;


import com.chengxiaoxiao.lizhiedu.security.service.UserLoginService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户登录处理类
 *
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/1 16:06 下午
 * @Description:
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Resource
    UserLoginService userLoginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userEntitySecurity = userLoginService.handleLogin(authentication);
        return new UsernamePasswordAuthenticationToken(userEntitySecurity, userEntitySecurity.getPassword(), userEntitySecurity.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
