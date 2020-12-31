package com.chengxiaoxiao.lizhiedu.auth.dto.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 获取用户信息返回类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/1 17:36
 */
@Data
@ApiModel("简单的用户信息")
public class SimpleUserInfoDto {
    /**
     * 角色数组
     */
    @ApiModelProperty("角色CODE数组")
    private List<String> roles;
    /**
     * 用户描述
     */
    @ApiModelProperty("用户描述")
    private String introduction;
    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;
    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String name;
}
