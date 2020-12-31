package com.chengxiaoxiao.lizhiedu.auth.dto.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色查询实体
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/3 14:59
 */
@Data
@ApiModel("角色查询实体")
public class RoleQuerySearch implements Serializable {
    @ApiModelProperty(value = "角色名", notes = "模糊查询")
    private String roleName;
    @ApiModelProperty(value = "角色编码", notes = "模糊查询")
    private String roleCode;

}
