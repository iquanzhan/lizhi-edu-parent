package com.chengxiaoxiao.lizhiedu.code.util;

import cn.hutool.core.date.DateUtil;
import com.chengxiaoxiao.lizhiedu.code.config.FileProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 文件工具类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/3/26 16:55
 */
@Component
public class FileUtils {

    @Resource
    private FileProperties fileProperties;


    /**
     * 获取路径，兼容linux和windows路径
     *
     * @param path
     * @return
     */
    public static String getRealPath(String path) {
        return path.replace("/", File.separator).replace("\\", File.separator);
    }

    /**
     * 设置文件下载响应
     *
     * @param response
     */
    public static void setDownloadResponse(HttpServletResponse response, String fileName) {
        //设置强制下载，不打开
        response.setContentType("application/force-download");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setHeader("Connection", "close");
    }

    /**
     * 文件下载
     *
     * @param filePath        文件路径
     * @param diaplayFileName 显示的文件名称
     * @param response        response
     */
    public static void donwloadFile(String filePath, String diaplayFileName, HttpServletRequest request,HttpServletResponse response) {
        File file = new File(filePath);
        if (file.exists()) {
            byte[] buffer = new byte[2048];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                diaplayFileName = getBrowserFileName(request, diaplayFileName);
                setDownloadResponse(response, diaplayFileName);

                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                ServletOutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 文件下载文件名乱码转换，兼容火狐和谷歌
     *
     * @param request
     * @param fileName
     * @return
     */
    public static String getBrowserFileName(HttpServletRequest request, String fileName) {
        try {
            if ("FF".equals(getBrowser(request))) {
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            return fileName;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (userAgent != null) {
            if (userAgent.contains("mise")) {
                return "IE";
            }
            if (userAgent.contains("firefox")) {
                return "FF";
            }
            if (userAgent.contains("safari")) {
                return "SF";
            }
        }
        return null;
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

        return year + File.separator + month + File.separator + day + File.separator + hour + File.separator + minute + File.separator + second + File.separator + millsecond;
    }

}
