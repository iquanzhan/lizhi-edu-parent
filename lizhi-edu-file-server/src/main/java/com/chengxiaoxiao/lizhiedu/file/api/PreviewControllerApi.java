package com.chengxiaoxiao.lizhiedu.file.api;

import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * 文件预览接口Swagger Api
 *
 * @Description:
 * @Author: Cheng Xiaoxiao
 * @Date: 2020-12-17 14:51:43
 */
@Api(tags = "文件预览模块", description = "实现对文件预览功能的支持")
public interface PreviewControllerApi {

    /**
     * Excel文件预览
     * @param path 路径信息
     * @param limit 记录条数
     * @return
     */
    @ApiOperation(value = "Excel预览")
    Result<List<Object>> excelPreview(@ApiParam(name = "path", value = "文件路径", required = true) String path,
                                      @ApiParam(name = "limit", value = "预览的记录条数") Integer limit);

}
