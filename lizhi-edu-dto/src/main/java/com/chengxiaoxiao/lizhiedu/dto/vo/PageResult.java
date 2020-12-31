package com.chengxiaoxiao.lizhiedu.dto.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页Model封装
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020-01-08 22:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页结果显示类")
public class PageResult<T> implements Serializable {
    @ApiModelProperty(value = "总记录数",example = "100")
    private Long total;
    @ApiModelProperty(value = "页码大小",example = "10")
    private Long size;
    @ApiModelProperty(value = "当前页",example = "1")
    private Long page;
    @ApiModelProperty("分页数据")
    private List<T> rows;
}
