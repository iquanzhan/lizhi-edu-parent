package com.chengxiaoxiao.lizhiedu.auth.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;
import com.chengxiaoxiao.lizhiedu.auth.mapper.RoleMapper;
import com.chengxiaoxiao.lizhiedu.auth.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleInfo> implements RoleService {

    @Override
    public List<RoleInfo> selectRoleByUserId(String userId) {
        return baseMapper.selectRoleByUserId(userId);
    }

}
