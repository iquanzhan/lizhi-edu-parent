package com.chengxiaoxiao.lizhiedu.auth.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "User对象", description = "用户表")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "请输入用户名")
    @Size(min = 4, max = 16, message = "用户名长度必须为4-16位之间")
    private String userName;

    @NotBlank(message = "请输入密码")
    @Size(min = 6, max = 16, message = "密码长度必须为6-16位之间")
    @JSONField(serialize = false)
    @ApiModelProperty(value = "密码", hidden = true)
    private String password;

    @NotBlank(message = "请输入用户姓名")
    @ApiModelProperty(value = "用户姓名")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "手机号码")
    private String phone;


    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "用户是否锁定")
    private Integer locked;

    @JSONField(serialize = false)
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除", hidden = true)
    @TableLogic
    private Boolean deleteStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "乐观锁版本号")
    @Version
    private Integer version;

}
