package com.chengxiaoxiao.lizhiedu.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
public interface RoleMapper extends BaseMapper<RoleInfo> {

    /**
     * 根据用户ID获取角色列表
     *
     * @param userId 用户ID
     * @return
     */
    List<RoleInfo> selectRoleByUserId(String userId);

}
