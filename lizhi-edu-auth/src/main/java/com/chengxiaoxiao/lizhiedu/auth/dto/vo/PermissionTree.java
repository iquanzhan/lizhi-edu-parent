package com.chengxiaoxiao.lizhiedu.auth.dto.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 权限树节点
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/4 8:58
 */
@Data
@ApiModel("权限树结构")
public class PermissionTree implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "所属上级")
    private String pid;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型(1:菜单,2:按钮)",example = "1")
    private Integer type;

    @ApiModelProperty(value = "权限编码")
    private String key;

    @ApiModelProperty(value = "访问路径")
    private String path;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "孩子节点")
    private List<PermissionTree> children;
}
