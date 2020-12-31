package com.chengxiaoxiao.lizhiedu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
public interface RoleService extends IService<RoleInfo> {

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId
     * @return
     */
    List<RoleInfo> selectRoleByUserId(String userId);


}
