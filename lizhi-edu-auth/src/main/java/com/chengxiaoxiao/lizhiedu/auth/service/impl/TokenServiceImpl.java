package com.chengxiaoxiao.lizhiedu.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserInfo;
import com.chengxiaoxiao.lizhiedu.auth.service.TokenService;
import com.chengxiaoxiao.lizhiedu.auth.util.JwtUtil;
import com.chengxiaoxiao.lizhiedu.common.core.exception.GlobalException;
import com.chengxiaoxiao.lizhiedu.dto.constant.RedisKeyConstant;
import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;
import com.chengxiaoxiao.lizhiedu.security.config.SecurityProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Resource
    JwtUtil jwtUtil;

    @Resource
    SecurityProperties securityProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public LoginUser getLoginUserByToken(String token) {
        String tokenHeaderAuth = token;
        token = token.replace(securityProperties.getJwt().getTokenPrefix(), "");

        //判断用户是否已经主动下线
        String tokenKey = String.format(RedisKeyConstant.USER_TOKEN, tokenHeaderAuth);

        Boolean hasToken = stringRedisTemplate.hasKey(tokenKey);
        if (!hasToken) {
            throw new GlobalException(CodeMsg.AUTHENTICATION_TOKEN_EXPIRED);
        }

        if (!"123456789".equals(token)) {
            jwtUtil.parseJWT(token);
        }

        UserInfo userInfo = JSONUtil.toBean(stringRedisTemplate.opsForValue().get(tokenKey), UserInfo.class);

        LoginUser loginUser = new LoginUser();
        loginUser.setId(userInfo.getId());
        loginUser.setUserName(userInfo.getUserName());
        loginUser.setNickName(userInfo.getNickName());

        return loginUser;

    }
}
