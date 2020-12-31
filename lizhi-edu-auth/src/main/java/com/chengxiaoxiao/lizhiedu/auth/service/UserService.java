package com.chengxiaoxiao.lizhiedu.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chengxiaoxiao.lizhiedu.auth.dto.vo.SimpleUserInfoDto;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserInfo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
public interface UserService extends IService<UserInfo> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return
     */
    UserInfo selectByUsername(String username);

    /**
     * 根据用户ID获取简单的用户信息，包含roles数组
     *
     * @param userId 用户ID
     * @return
     */
    SimpleUserInfoDto loadSimpleUserById(String userId);

    /**
     * 添加用户<>需要判断用户名重复问题</>
     *
     * @param userInfo
     */
    void addUser(UserInfo userInfo);
}
