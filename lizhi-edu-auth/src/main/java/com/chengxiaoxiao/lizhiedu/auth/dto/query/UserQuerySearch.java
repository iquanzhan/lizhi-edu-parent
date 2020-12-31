package com.chengxiaoxiao.lizhiedu.auth.dto.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/3 8:45
 */
@Data
@ApiModel("用户查询实体")
public class UserQuerySearch implements Serializable {
    @ApiModelProperty(value = "用户名", notes = "模糊查询")
    private String username;
    @ApiModelProperty(value = "用户姓名", notes = "模糊查询")
    private String nickName;
}
