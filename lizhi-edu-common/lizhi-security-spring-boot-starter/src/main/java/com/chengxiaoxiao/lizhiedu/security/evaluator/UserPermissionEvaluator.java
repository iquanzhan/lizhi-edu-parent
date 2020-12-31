package com.chengxiaoxiao.lizhiedu.security.evaluator;


import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 自定义权限注解验证
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:39 下午
 * @Description:
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
    /**
     * hasPermission 进行更复杂的鉴权
     *
     * @param authentication 用户认证对象
     * @param targetUrl      目标url
     * @param permission     所拥有的的权限
     * @return 是否验证通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}

