package com.chengxiaoxiao.lizhiedu.auth.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "权限对象", description = "权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "所属上级")
    @NotNull(message = "请选择上级节点")
    private String pid;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "请输入权限名称")
    private String name;

    @ApiModelProperty(value = "类型(0:应用1:菜单,2:按钮)",example = "1")
    @NotNull(message = "请选择权限类型")
    private Integer type;

    @ApiModelProperty(value = "权限编码")
    @NotNull(message = "请选择权限标识")
    private String key;

    @ApiModelProperty(value = "访问路径")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "排序信息",example = "1")
    private Integer sort;

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
    private Integer version;
}
