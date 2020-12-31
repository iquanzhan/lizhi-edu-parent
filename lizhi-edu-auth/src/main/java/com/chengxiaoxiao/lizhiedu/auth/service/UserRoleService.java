package com.chengxiaoxiao.lizhiedu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserRole;

import java.util.List;

/**
 * <p>
 * 用户角色关联 服务类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-18
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 用户角色绑定成功
     *
     * @param userId  用户ID
     * @param roleIds 角色ID数组
     */
    void bindRolebyUserId(String userId, List<String> roleIds);

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return
     */
    List<RoleInfo> getRoleListByUserId(String userId);
}
