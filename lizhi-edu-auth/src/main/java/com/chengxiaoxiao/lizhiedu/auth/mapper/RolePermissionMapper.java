package com.chengxiaoxiao.lizhiedu.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;
import com.chengxiaoxiao.lizhiedu.auth.entity.RolePermission;

import java.util.List;

/**
 * <p>
 * 角色权限关联 Mapper 接口
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-18
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 根据角色ID查询其权限信息
     *
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionListByRoleId(String roleId);
}
