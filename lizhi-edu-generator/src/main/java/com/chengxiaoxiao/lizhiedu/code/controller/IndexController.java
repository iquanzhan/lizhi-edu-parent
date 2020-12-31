package com.chengxiaoxiao.lizhiedu.code.controller;

import com.chengxiaoxiao.lizhiedu.code.service.CodeGeneratorService;
import com.chengxiaoxiao.lizhiedu.code.vo.query.DatabaseConfigQuery;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 主页控制器
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/12 16:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    CodeGeneratorService codeGeneratorService;

    /**
     * 生成前端代码
     */
    @PostMapping("/frontend")
    public String generateFrontEndCode(@RequestBody DatabaseConfigQuery query) {
        return codeGeneratorService.generateFrontEnd(query);
    }

    /**
     * 生成前端代码
     */
    @PostMapping("/backend")
    public String generateBackEndCode(@RequestBody DatabaseConfigQuery query) {
        return codeGeneratorService.generateBackEnd(query);
    }
}
