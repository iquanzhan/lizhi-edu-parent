package com.chengxiaoxiao.lizhiedu.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 获取所有的权限
     *
     * @return 权限值列表
     */
    List<String> selectAllPermissionValue();


    /**
     * 根据用户ID获取所拥有的权限
     *
     * @param userId 用户ID
     * @return 权限值列表
     */
    List<String> selectPermissionValueByUserId(String userId);

    /**
     * 根据用户ID和权限类型获取有权限的列表信息
     *
     * @param userId 用户ID
     * @param type   权限类型
     * @return
     */
    List<Permission> getPermissionByUserIdType(@Param("userId") String userId, @Param("type") int type);
}
