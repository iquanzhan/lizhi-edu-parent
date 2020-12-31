package com.chengxiaoxiao.lizhiedu.auth.api;

import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * TOKEN模块
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/12/2 10:31
 */
@Api(tags = "TOKEN模块", description = "实现对TOKEN数据的基本操作")
public interface TokenControllerApi {

    /**
     * 根据Token获取用户登录信息
     * @param token token
     * @return
     */
    @ApiOperation(value = "根据Token获取用户登录信息")
    Result<LoginUser> getUserInfoByToken(@ApiParam(name = "token", value = "用户TOKEN", required = true) String token);
}
