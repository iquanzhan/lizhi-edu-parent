package com.chengxiaoxiao.lizhiedu.auth.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "角色对象", description = "")
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "请输入角色名称")
    private String name;

    @ApiModelProperty(value = "角色编码")
    @NotBlank(message = "请输入角色编码")
    private String key;

    @ApiModelProperty(value = "备注")
    private String remark;

    @JSONField(serialize = false)
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean deleteStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @ApiModelProperty(value = "乐观锁版本号")
    private Integer version;
}
