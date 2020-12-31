package com.chengxiaoxiao.lizhiedu.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserRole;

import java.util.List;

/**
 * <p>
 * 用户角色关联 Mapper 接口
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-18
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据yonghuID查询角色列表
     *
     * @param userId
     * @return
     */
    List<RoleInfo> selectRoleListByUserId(String userId);
}
