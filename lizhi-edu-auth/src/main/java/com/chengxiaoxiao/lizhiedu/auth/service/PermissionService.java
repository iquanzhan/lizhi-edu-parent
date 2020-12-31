package com.chengxiaoxiao.lizhiedu.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chengxiaoxiao.lizhiedu.auth.dto.vo.PermissionTree;
import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据用户ID，获取权限信息
     *
     * @param userId
     * @return
     */
    List<String> selectPermissionValueByUserId(String userId);

    /**
     * 树形展示权限信息
     *
     * @return
     */
    List<PermissionTree> tree();

    /**
     * 递归删除
     *
     * @param id
     */
    void deleteAll(String id);

    /**
     * 根据用户ID获取有权限的应用列表
     *
     * @param userId 用户ID
     * @return
     */
    List<Permission> getApplicationList(String userId);


    /**
     * 根据用户ID列表删除
     *
     * @param idList 用户ID列表
     */
    void deleteByIds(List<String> idList);
}
