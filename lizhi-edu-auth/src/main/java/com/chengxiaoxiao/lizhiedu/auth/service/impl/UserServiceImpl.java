package com.chengxiaoxiao.lizhiedu.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengxiaoxiao.lizhiedu.auth.dto.vo.SimpleUserInfoDto;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserInfo;
import com.chengxiaoxiao.lizhiedu.auth.mapper.UserMapper;
import com.chengxiaoxiao.lizhiedu.auth.service.RoleService;
import com.chengxiaoxiao.lizhiedu.auth.service.UserService;
import com.chengxiaoxiao.lizhiedu.common.core.exception.GlobalException;
import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserInfo selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<UserInfo>().eq("user_name", username));
    }

    @Override
    public SimpleUserInfoDto loadSimpleUserById(String userId) {
        UserInfo userInfo = baseMapper.selectById(userId);
        if (null == userInfo) {
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
        SimpleUserInfoDto userInfoDto = new SimpleUserInfoDto();
        BeanUtil.copyProperties(userInfo, userInfoDto, true);
        userInfoDto.setIntroduction(userInfo.getRemark());


        List<RoleInfo> listRole = roleService.selectRoleByUserId(userId);
        List<String> roles = listRole.stream().map(item -> item.getKey()).collect(Collectors.toList());
        if (roles.size() <= 0) {
            roles.add("");
        }
        userInfoDto.setRoles(roles);
        return userInfoDto;
    }

    @Override
    public void addUser(UserInfo userInfo) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<UserInfo>().eq("user_name", userInfo.getUserName());
        UserInfo userInfoDb = baseMapper.selectOne(queryWrapper);
        if (userInfoDb != null) {
            throw new GlobalException(CodeMsg.USER_NAME_EXIST);
        }

        save(userInfo);
    }

}
