package com.chengxiaoxiao.lizhiedu.file.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 启动时文件夹创建.及文件处理相关信息
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/12/03 16:18
 */
@Component
public class FileApplicationRunner implements ApplicationRunner, Ordered {

    private final Log log = LogFactory.get(this.getClass().getName());

    @Autowired
    private FileProperties fileProperties;

    @Override
    public void run(ApplicationArguments args) {
        try {
            if (!FileUtil.exist(fileProperties.getSavePath())) {
                FileUtil.mkdir(fileProperties.getSavePath());
                log.info("文件上传目录创建成功");
            }
        } catch (Exception ex) {
            log.info("创建上传文件目录失败,请检查目录权限");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
