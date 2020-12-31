package com.chengxiaoxiao.lizhiedu.file.service.impl;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.chengxiaoxiao.lizhiedu.file.config.FileProperties;
import com.chengxiaoxiao.lizhiedu.file.dto.vo.FileCodeMsg;
import com.chengxiaoxiao.lizhiedu.file.listen.ExcelListener;
import com.chengxiaoxiao.lizhiedu.file.service.PreviewService;
import com.chengxiaoxiao.lizhiedu.common.core.exception.GlobalException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;

/**
 * 文件预览业务实现类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/17 16:13
 * @Version 1.0
 */
@Service
public class PreviewServiceImpl implements PreviewService {

    @Resource
    FileProperties fileProperties;


    @SneakyThrows
    @Override
    public List<Object> previewExcel(String path, Integer limit) {
        String filePath = fileProperties.getSavePath() + path.replace("/upload", "");
        if (!FileUtil.exist(filePath)) {
            throw new GlobalException(FileCodeMsg.FILE_PATH_NOT_FOUND);
        }
        Sheet sheet = new Sheet(1, 0);
        ExcelListener listener = new ExcelListener(limit);
        ExcelReader reader = new ExcelReader(new BufferedInputStream(new FileInputStream(filePath)), null, listener);
        reader.read(sheet);

        return listener.getRows();
    }
}
