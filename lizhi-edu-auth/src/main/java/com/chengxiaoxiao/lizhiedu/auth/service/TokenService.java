package com.chengxiaoxiao.lizhiedu.auth.service;


import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;

/**
 * <p>
 * TOKEN 服务类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-12-02
 */
public interface TokenService{

    /**
     * 根据TOKEN获取登录用户信息
     * @param token TOKEN
     * @return 登录用户
     */
    LoginUser getLoginUserByToken(String token);
}
