package com.chengxiaoxiao.lizhiedu.login.service;


import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Token远程调用
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020-12-02 21:08
 */
@FeignClient("lizhi-edu-auth")
public interface UserTokenService {
    /**
     * 根据TOKEN获取登录用户信息
     * @param token TOKEN
     * @return
     */
    @GetMapping("/token/user")
    public Result<LoginUser> getUserInfoByToken(@RequestParam(required = true,name = "token") String token);
}
