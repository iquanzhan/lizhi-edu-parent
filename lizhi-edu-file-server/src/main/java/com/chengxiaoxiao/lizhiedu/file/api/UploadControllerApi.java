package com.chengxiaoxiao.lizhiedu.file.api;

import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import com.chengxiaoxiao.lizhiedu.file.dto.vo.UploadFileDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传接口Swagger Api
 *
 * @Description:
 * @Author: Cheng Xiaoxiao
 * @Date: 2020-12-03 13:12:43
 */
@Api(tags = "文件上传模块", description = "实现对文件上传功能的支持")
public interface UploadControllerApi {

    /**
     * 单个文件上传
     *
     * @return
     */
    @ApiOperation(value = "单个文件上传")
    Result<UploadFileDetailVo> upload(@ApiParam(name = "file", value = "文件信息", required = true)MultipartFile file);

    /**
     * 多个文件上传
     *
     * @return
     */
    @ApiOperation(value = "多个文件上传")
    Result<List<UploadFileDetailVo>> uploadMult(@ApiParam(name = "files", value = "文件数组", required = true) MultipartFile[] files);
}
