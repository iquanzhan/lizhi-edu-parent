package com.chengxiaoxiao.lizhiedu.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserRole;
import com.chengxiaoxiao.lizhiedu.auth.mapper.UserRoleMapper;
import com.chengxiaoxiao.lizhiedu.auth.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public void bindRolebyUserId(String userId, List<String> roleIds) {
        //删除原先的关系
        baseMapper.delete(new QueryWrapper<UserRole>().eq("user_id", userId));

        //添加关系
        List<UserRole> userRoles = new ArrayList<>();
        for (String roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoles.add(userRole);
        }

        this.saveBatch(userRoles);
    }

    @Override
    public List<RoleInfo> getRoleListByUserId(String userId) {
        return baseMapper.selectRoleListByUserId(userId);
    }
}
