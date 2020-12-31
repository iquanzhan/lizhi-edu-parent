package com.chengxiaoxiao.lizhiedu.auth.controller;


import com.chengxiaoxiao.lizhiedu.auth.api.RolePermissionControllerApi;
import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;
import com.chengxiaoxiao.lizhiedu.auth.service.RolePermissionService;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户角色关联接口
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/role-permission")
public class RolePermissionController implements RolePermissionControllerApi {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    @GetMapping("/permission/{roleId}")
    public Result<List<Permission>> getPermissionListByRoleId(@PathVariable String roleId) {
        List<Permission> permissions = rolePermissionService.selectPermissionListByRoleId(roleId);
        return Result.success(permissions);
    }

    @Override
    @PostMapping("/permission/{roleId}")
    public Result bindPermission(@PathVariable String roleId, @RequestBody List<String> permissionIds) {
        rolePermissionService.bindPermissionByRoleId(roleId, permissionIds);
        return Result.success("权限绑定成功");
    }
}

