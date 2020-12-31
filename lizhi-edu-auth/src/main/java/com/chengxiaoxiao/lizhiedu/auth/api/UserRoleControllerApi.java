package com.chengxiaoxiao.lizhiedu.auth.api;


import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * 角色接口
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/19 10:57
 */
@Api(tags = "用户角色关联模块", description = "实现对用户角色关联数据的基本操作")
public interface UserRoleControllerApi {


    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    @ApiOperation(value = "根据用户ID获取角色列表")
    Result<List<RoleInfo>> getRoleListByUserId(@ApiParam(name = "userId", value = "用户ID", type = "path") String userId);


    /**
     * 根据用户ID绑定角色信息
     *
     * @param userId  用户ID
     * @param roleIds 角色ID数组
     * @return
     */
    @ApiOperation(value = "根据用户ID绑定角色信息")
    Result bindRole(@ApiParam(name = "userId", value = "用户ID", type = "path") String userId,
                    @ApiParam(name = "roleIds", value = "角色ID数组") List<String> roleIds);
}
