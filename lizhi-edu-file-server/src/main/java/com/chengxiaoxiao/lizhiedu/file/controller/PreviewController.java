package com.chengxiaoxiao.lizhiedu.file.controller;

import com.chengxiaoxiao.lizhiedu.file.api.PreviewControllerApi;
import com.chengxiaoxiao.lizhiedu.file.service.PreviewService;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 文件上传 接口控制器
 *
 * @Description:
 * @Author: Cheng Xiaoxiao
 * @Date: 2020-12-03 13:14:43
 */
@RestController
@RequestMapping("/preview")
public class PreviewController implements PreviewControllerApi {

    @Resource
    PreviewService previewService;

    @Override
    @GetMapping("/excel")
    public Result<List<Object>> excelPreview(String path, @RequestParam(required = false) Integer limit) {
        List<Object> datas = previewService.previewExcel(path, limit);
        return Result.success(datas);
    }
}
