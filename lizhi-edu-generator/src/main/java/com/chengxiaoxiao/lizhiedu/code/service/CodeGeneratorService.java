package com.chengxiaoxiao.lizhiedu.code.service;

import com.chengxiaoxiao.lizhiedu.code.vo.query.DatabaseConfigQuery;

/**
 * 代码生成器业务逻辑层
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/13 16:08
 * @Version 1.0
 */
public interface CodeGeneratorService {

    /**
     * 生成前端代码
     *
     * @param query 数据库参数
     */
    String generateFrontEnd(DatabaseConfigQuery query);

    /**
     * 生成后端代码
     *
     * @param query 数据库参数
     */
    String generateBackEnd(DatabaseConfigQuery query);
}
