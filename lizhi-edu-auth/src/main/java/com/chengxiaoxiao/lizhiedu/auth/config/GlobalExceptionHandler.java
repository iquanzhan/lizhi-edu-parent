package com.chengxiaoxiao.lizhiedu.auth.config;

import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 鉴权中心全局异常处理
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/12/2 13:10
 * @Version 1.0
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理系统逻辑产生的异常
     *
     * @param e 异常对象
     * @return 异常信息JSON
     */
    @ExceptionHandler(value = ExpiredJwtException.class)
    public Result<String> handleGlobalException(ExpiredJwtException e) {
        log.error(e.getMessage(), e);
        return Result.error(CodeMsg.AUTHENTICATION_TOKEN_EXPIRED);
    }
}
