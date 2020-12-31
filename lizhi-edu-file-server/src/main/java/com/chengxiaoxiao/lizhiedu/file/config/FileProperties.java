package com.chengxiaoxiao.lizhiedu.file.config;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件配置信息
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/3 11:19
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "lizhi.file")
public class FileProperties {
    /**
     * 保存路径
     */
    private String savePath = "./upload";

    public String getSavePath() {
        Path normalize = Paths.get(savePath).toAbsolutePath().normalize();
        return StrUtil.appendIfMissing(normalize.toString(), "/");
    }
}
