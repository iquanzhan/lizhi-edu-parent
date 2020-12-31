package com.chengxiaoxiao.lizhiedu.auth.api;

import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;
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
 * @Date: 2020/6/19 14:57
 */
@Api(tags = "角色权限关联模块", description = "实现对角色权限关联数据的基本操作")
public interface RolePermissionControllerApi {

    /**
     * 根据角色ID获取权限列表
     *
     * @param roleId 角色ID
     * @return
     */
    @ApiOperation(value = "根据角色ID获取权限列表")
    Result<List<Permission>> getPermissionListByRoleId(@ApiParam(name = "userId", value = "角色ID", type = "path") String roleId);


    /**
     * 根据用户ID绑定角色信息
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID数组
     * @return
     */
    @ApiOperation(value = "根据角色ID绑定权限信息")
    Result bindPermission(@ApiParam(name = "roleId", value = "角色ID", type = "path") String roleId,
                          @ApiParam(name = "permissionIds", value = "权限ID数组") List<String> permissionIds);
}
