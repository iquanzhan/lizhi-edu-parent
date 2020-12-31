package com.chengxiaoxiao.lizhiedu.file.controller;

import com.chengxiaoxiao.lizhiedu.file.api.UploadControllerApi;
import com.chengxiaoxiao.lizhiedu.file.dto.vo.UploadFileDetailVo;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import com.chengxiaoxiao.lizhiedu.file.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 文件上传 接口控制器
 *
 * @Description:
 * @Author: Cheng Xiaoxiao
 * @Date: 2020-12-03 13:14:43
 */
@RestController
@RequestMapping("/file")
public class UploadController implements UploadControllerApi {

    @Resource
    private UploadService uploadService;
    @Resource
    private HttpServletRequest request;

    @Override
    @PostMapping("/single")
    public Result<UploadFileDetailVo> upload(@RequestParam(value = "file") MultipartFile file) {
        return Result.success(uploadService.upload(request, file));
    }

    @Override
    @PostMapping("/multi")
    public Result<List<UploadFileDetailVo>> uploadMult(@RequestParam(value = "files") MultipartFile[] files) {
        return Result.success(uploadService.upload(request, files));
    }
}
