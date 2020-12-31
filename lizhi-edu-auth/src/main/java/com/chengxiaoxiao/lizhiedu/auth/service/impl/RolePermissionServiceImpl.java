package com.chengxiaoxiao.lizhiedu.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;
import com.chengxiaoxiao.lizhiedu.auth.entity.RolePermission;
import com.chengxiaoxiao.lizhiedu.auth.mapper.RolePermissionMapper;
import com.chengxiaoxiao.lizhiedu.auth.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色权限关联 服务实现类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-18
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public List<Permission> selectPermissionListByRoleId(String roleId) {
        return baseMapper.selectPermissionListByRoleId(roleId);
    }

    @Override
    public void bindPermissionByRoleId(String roleId, List<String> permissionIds) {
        //删除原先的关系
        baseMapper.delete(new QueryWrapper<RolePermission>().eq("role_id", roleId));

        //添加关系
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        }

        this.saveBatch(rolePermissions);
    }
}
