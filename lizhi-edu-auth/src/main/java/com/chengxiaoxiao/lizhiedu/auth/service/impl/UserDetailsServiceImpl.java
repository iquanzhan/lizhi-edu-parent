package com.chengxiaoxiao.lizhiedu.auth.service.impl;


import com.chengxiaoxiao.lizhiedu.auth.entity.SecurityUser;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserInfo;
import com.chengxiaoxiao.lizhiedu.auth.service.PermissionService;
import com.chengxiaoxiao.lizhiedu.auth.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-16
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        UserInfo userInfo = userService.selectByUsername(username);

        // 判断用户是否存在
        if (null == userInfo) {
            return null;
        }
        // 返回UserDetails实现类
        UserInfo curUserInfo = new UserInfo();
        BeanUtils.copyProperties(userInfo, curUserInfo);

        List<String> authorities = permissionService.selectPermissionValueByUserId(userInfo.getId());
        SecurityUser securityUser = new SecurityUser(curUserInfo);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}
