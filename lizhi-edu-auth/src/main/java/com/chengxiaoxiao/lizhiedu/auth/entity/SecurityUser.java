package com.chengxiaoxiao.lizhiedu.auth.entity;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 安全认证用户详情信息
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@Data
@Slf4j
public class SecurityUser implements UserDetails {

    /**
     * 当前登录用户
     */
    private transient UserInfo currentUserInfoInfo;

    /**
     * 此用户的权限信息
     */
    private List<String> permissionValueList;

    public SecurityUser() {
    }

    public SecurityUser(UserInfo userInfo) {
        if (userInfo != null) {
            this.currentUserInfoInfo = userInfo;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String permissionValue : permissionValueList) {
            if (StringUtils.isEmpty(permissionValue)) {
                continue;
            }
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return currentUserInfoInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return currentUserInfoInfo.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
