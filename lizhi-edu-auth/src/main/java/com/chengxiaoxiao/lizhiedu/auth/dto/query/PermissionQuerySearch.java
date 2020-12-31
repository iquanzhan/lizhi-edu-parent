package com.chengxiaoxiao.lizhiedu.auth.dto.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限查询实体
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/3 16:12
 */
@Data
@ApiModel("权限查询实体")
public class PermissionQuerySearch implements Serializable {
    @ApiModelProperty(value = "所属上级")
    private String pid;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
    private Integer type;

    @ApiModelProperty(value = "权限编码")
    private String key;

    @ApiModelProperty(value = "访问路径")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;
}
