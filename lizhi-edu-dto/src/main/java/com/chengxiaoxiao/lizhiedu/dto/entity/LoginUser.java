package com.chengxiaoxiao.lizhiedu.dto.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/22 10:52
 */
@Data
public class LoginUser implements Serializable {
    private String id;
    private String userName;
    private String nickName;
}
