package com.chengxiaoxiao.lizhiedu.auth.controller;


import com.chengxiaoxiao.lizhiedu.auth.api.TokenControllerApi;
import com.chengxiaoxiao.lizhiedu.auth.service.TokenService;
import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * TOKEN接口
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-12-02
 */
@RestController
@RequestMapping("/token")
public class TokenController implements TokenControllerApi {

    @Resource
    TokenService tokenService;

    @Override
    @GetMapping("/user")
    public Result<LoginUser> getUserInfoByToken(@RequestParam(required = true,name = "token") String token) {
        return Result.success(tokenService.getLoginUserByToken(token));
    }
}

