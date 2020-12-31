package com.chengxiaoxiao.lizhiedu.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;
import com.chengxiaoxiao.lizhiedu.auth.entity.RolePermission;

import java.util.List;

/**
 * <p>
 * 角色权限关联 服务类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-18
 */
public interface RolePermissionService extends IService<RolePermission> {

    /**
     * 根据角色ID查询权限列表
     *
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionListByRoleId(String roleId);


    /**
     * 根据角色信息绑定权限信息
     *
     * @param roleId
     * @param permissionIds
     */
    void bindPermissionByRoleId(String roleId, List<String> permissionIds);

}
