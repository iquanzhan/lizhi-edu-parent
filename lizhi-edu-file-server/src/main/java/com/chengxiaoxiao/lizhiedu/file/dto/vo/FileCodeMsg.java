package com.chengxiaoxiao.lizhiedu.file.dto.vo;

import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;

/**
 * 文件服务错误码提示
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/3 13:28
 * @Version 1.0
 */
public class FileCodeMsg extends CodeMsg {

    public static CodeMsg FILE_NOT_FOUND = new CodeMsg("A0301", "请上传文件");
    public static CodeMsg FILE_CONTENT_EMPTY = new CodeMsg("A0302", "文件：<%>,内容为空");
    public static CodeMsg FILE_UPLOAD_FAILED = new CodeMsg("A0303", "文件上传失败:%s");
    public static CodeMsg FILE_PATH_NOT_FOUND = new CodeMsg("A0304", "文件不存在");
}
