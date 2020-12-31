package com.chengxiaoxiao.lizhiedu.common.core.util;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HTTP结果返回工具类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/2 8:53 下午
 * @Description:
 */
public class ResultUtil {
    public static void responseJson(HttpServletResponse response, Object resultData) {

        String json = JSONObject.toJSONString(resultData);

        response.setContentType("text/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
