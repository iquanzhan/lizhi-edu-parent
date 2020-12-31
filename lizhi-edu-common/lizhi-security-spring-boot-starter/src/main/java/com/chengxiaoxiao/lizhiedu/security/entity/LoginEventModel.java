package com.chengxiaoxiao.lizhiedu.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录事件模型对象
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/3 13:33
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginEventModel {
    private HttpServletRequest request;
    private Authentication authentication;
    private Exception exception;
}
