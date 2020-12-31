package com.chengxiaoxiao.lizhiedu.code.config;

import cn.hutool.core.io.FileUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件属性对象
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/13 16:28
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "lizhi.code")
public class FileProperties {
    private String uploadPath;

    public String getUploadPath() {
        Path normalize = Paths.get(this.uploadPath).toAbsolutePath().normalize();
        return normalize.toString();
    }

    /**
     * 获取文件相对于upload文件的相对目录
     * @param moduleZipFile 全路径
     * @return 相对路径
     */
    public String getAbsUploadPath(String moduleZipFile) {
        String absolutePath = FileUtil.getAbsolutePath(moduleZipFile);
        String uploadPath = FileUtil.getAbsolutePath(getUploadPath());
        return absolutePath.replace(uploadPath, "");
    }
}
