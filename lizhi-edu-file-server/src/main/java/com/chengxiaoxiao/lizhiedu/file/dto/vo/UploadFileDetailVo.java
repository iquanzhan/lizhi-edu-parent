package com.chengxiaoxiao.lizhiedu.file.dto.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 上传文件详情
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/3 13:17
 * @Version 1.0
 */
@Data
@ApiModel("上传文件展示对象")
public class UploadFileDetailVo {
    @ApiModelProperty("文件访问地址")
    private String url;
}
