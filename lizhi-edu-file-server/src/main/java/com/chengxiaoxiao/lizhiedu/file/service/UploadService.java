package com.chengxiaoxiao.lizhiedu.file.service;

import com.chengxiaoxiao.lizhiedu.file.dto.vo.UploadFileDetailVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件上传服务类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/3 13:23
 * @Version 1.0
 */
public interface UploadService {
    /**
     * 单个文件上传
     *
     * @param request 请求对象
     * @param file 文件对象
     * @return 保存后的VO对象
     */
    UploadFileDetailVo upload(HttpServletRequest request, MultipartFile file);

    /**
     * 文件批量上传
     *
     * @param request 请求对象
     * @param file 文件对象
     * @return 保存后的VO对象
     */
    List<UploadFileDetailVo> upload(HttpServletRequest request, MultipartFile[] file);
}
