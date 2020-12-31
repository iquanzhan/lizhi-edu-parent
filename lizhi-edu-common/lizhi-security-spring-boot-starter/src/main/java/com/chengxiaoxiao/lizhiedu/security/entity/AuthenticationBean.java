package com.chengxiaoxiao.lizhiedu.security.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户登录对象
 *
 * @Author: Cheng XiaoXiao
 * @Date: 2020/11/03 14:21:13
 * @Description:
 */
@Getter
@Setter
public class AuthenticationBean {
    private String userName;
    private String password;
}
