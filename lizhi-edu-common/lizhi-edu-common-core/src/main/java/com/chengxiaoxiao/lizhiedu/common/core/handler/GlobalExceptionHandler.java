package com.chengxiaoxiao.lizhiedu.common.core.handler;

import com.chengxiaoxiao.lizhiedu.common.core.exception.GlobalException;
import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;


/**
 * 统一异常处理
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/1 16:33
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
    @ExceptionHandler(value = GlobalException.class)
    public Result<String> handleGlobalException(GlobalException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCm());
    }

    /**
     * 处理JSR349 校验参数的异常
     *
     * @param ex 异常对象
     * @return 异常信息JSON
     */
    @ExceptionHandler(value = BindException.class)
    public Result<String> handleBindException(BindException ex) {
        log.error(ex.getMessage(), ex);
        List<ObjectError> errors = ex.getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();

        return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
    }


    /**
     * 处理JSR349 校验异常
     *
     * @param ex 异常对象
     * @return 异常信息JSON
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<String> handleNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        ObjectError error = errors.get(0);
        String msg = error.getDefaultMessage();

        return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
    }


    /**
     * 处理404，路径找不到异常
     *
     * @param e 异常对象
     * @return 异常信息JSON
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Result<String> handlePageNotFoundException(NoHandlerFoundException e) {
        log.error(e.getMessage(), e);
        return Result.success(CodeMsg.NOT_PAGE_FOUND);
    }


    /**
     * 处理其他异常信息
     *
     * @param e 异常对象
     * @return 异常信息JSON
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        if (log.isDebugEnabled()) {
            e.printStackTrace();
        }
        log.error(e.getMessage(), e);
        return Result.error(CodeMsg.OTHER_ERROR.fillArgs(e.getMessage()));
    }
}
