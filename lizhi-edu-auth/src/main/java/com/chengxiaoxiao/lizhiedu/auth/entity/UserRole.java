package com.chengxiaoxiao.lizhiedu.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色权限关联
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("USER_ROLE")
@ApiModel(value = "USER_ROLE对象", description = "用户角色关联")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "角色ID")
    @TableField("ROLE_ID")
    private String roleId;

    @ApiModelProperty(value = "权限ID")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("DELETE_STATUS")
    @TableLogic
    private Boolean deleteStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
