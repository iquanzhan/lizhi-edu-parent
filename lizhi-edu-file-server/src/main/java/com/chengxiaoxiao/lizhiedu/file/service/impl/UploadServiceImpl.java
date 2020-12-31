package com.chengxiaoxiao.lizhiedu.file.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.chengxiaoxiao.lizhiedu.file.config.FileProperties;
import com.chengxiaoxiao.lizhiedu.file.dto.vo.FileCodeMsg;
import com.chengxiaoxiao.lizhiedu.file.dto.vo.UploadFileDetailVo;
import com.chengxiaoxiao.lizhiedu.file.service.UploadService;
import com.chengxiaoxiao.lizhiedu.common.core.exception.GlobalException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传业务实现类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/3 13:24
 * @Version 1.0
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Resource
    FileProperties fileProperties;


    @Override
    public UploadFileDetailVo upload(HttpServletRequest request, MultipartFile file) {
        if (file == null) {
            throw new GlobalException(FileCodeMsg.FILE_NOT_FOUND);
        }

        //创建保存文件夹
        String dataAbsPath = generateRealFilePath();
        String fileDirPath = fileProperties.getSavePath() + dataAbsPath;
        if (!FileUtil.exist(fileDirPath)) {
            FileUtil.mkdir(fileDirPath);
        }

        UploadFileDetailVo uploadFileDetailVo = saveAndGetFileInfo(file, fileDirPath);

        return uploadFileDetailVo;
    }

    @Override
    public List<UploadFileDetailVo> upload(HttpServletRequest request, MultipartFile[] files) {
        //创建保存文件夹
        String dataAbsPath = generateRealFilePath();
        String fileDirPath = fileProperties.getSavePath() + dataAbsPath;
        if (!FileUtil.exist(fileDirPath)) {
            FileUtil.mkdir(fileDirPath);
        }
        List<UploadFileDetailVo> listFileInfo = new LinkedList<>();
        for (MultipartFile file : files) {
            listFileInfo.add(saveAndGetFileInfo(file, fileDirPath));
        }
        return listFileInfo;
    }


    /**
     * 保存文件并返回FileInfo信息
     *
     * @param file        文件对象
     * @param fileDirPath 文件需要保存的路径
     * @return
     */
    private UploadFileDetailVo saveAndGetFileInfo(MultipartFile file, String fileDirPath) {
        String fileName = file.getOriginalFilename();
        if (file.isEmpty()) {
            throw new GlobalException(FileCodeMsg.FILE_CONTENT_EMPTY.fillArgs(fileName));
        }

        String fileType = StrUtil.subAfter(fileName, ".", true);

        //系统中保存的文件名称
        String uploadFileName = UUID.randomUUID().toString().replace("-", "") + "." + fileType;
        //在硬盘上保存的路径
        String localFilePath = StrUtil.appendIfMissing(fileDirPath, "/") + uploadFileName;

        try {
            file.transferTo(new File(localFilePath));
        } catch (IOException e) {
            throw new GlobalException(FileCodeMsg.FILE_UPLOAD_FAILED.fillArgs(e.getMessage()));
        }
        UploadFileDetailVo fileInfo = new UploadFileDetailVo();
        fileInfo.setUrl("/upload/" + localFilePath.replace(fileProperties.getSavePath(), ""));
        return fileInfo;
    }


    /**
     * 根据当前日期获取相对路径
     *
     * @return
     */
    public static String generateRealFilePath() {
        //按照年月日时分秒进行保存
        Date now = DateUtil.date();
        int year = DateUtil.year(now);
        int month = DateUtil.month(now) + 1;
        int day = DateUtil.dayOfMonth(now);
        int hour = DateUtil.hour(now, true);
        int minute = DateUtil.minute(now);
        int second = DateUtil.second(now);
        int millsecond = DateUtil.millsecond(now);

        return year + "/" + month + "/" + day + "/" + hour + "/" + minute + "/" + second + "/" + millsecond;
    }
}
