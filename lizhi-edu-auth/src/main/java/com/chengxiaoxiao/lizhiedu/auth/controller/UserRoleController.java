package com.chengxiaoxiao.lizhiedu.auth.controller;


import com.chengxiaoxiao.lizhiedu.auth.api.UserRoleControllerApi;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;
import com.chengxiaoxiao.lizhiedu.auth.service.UserRoleService;
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
@RequestMapping("/user-role")
public class UserRoleController implements UserRoleControllerApi {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    @GetMapping("/role/{userId}")
    public Result<List<RoleInfo>> getRoleListByUserId(@PathVariable String userId) {
        return Result.success(userRoleService.getRoleListByUserId(userId));
    }

    /**
     * 为用户绑定角色信息
     *
     * @param userId  用户ID
     * @param roleIds 角色ID数组
     * @return
     */
    @Override
    @PostMapping("/role/{userId}")
    public Result bindRole(@PathVariable String userId, @RequestBody List<String> roleIds) {
        userRoleService.bindRolebyUserId(userId, roleIds);
        return Result.success("用户角色绑定成功");
    }
}

