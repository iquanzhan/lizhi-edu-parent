package com.chengxiaoxiao.lizhiedu.file.service;

import java.util.List;

/**
 * 文件预览服务类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/17 16:13
 * @Version 1.0
 */
public interface PreviewService {

    /**
     * 根据路径和条数信息读取Excel记录
     *
     * @param path  路径
     * @param limit 限制条数
     * @return
     */
    List<Object> previewExcel(String path, Integer limit);
}
