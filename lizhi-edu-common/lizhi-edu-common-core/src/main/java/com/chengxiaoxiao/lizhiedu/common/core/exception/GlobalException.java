package com.chengxiaoxiao.lizhiedu.common.core.exception;


import com.chengxiaoxiao.lizhiedu.dto.vo.CodeMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一异常
 *
 * @Description: 所有的异常都抛出此异常
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/1 16:33
 */
@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 7331906799009231998L;
    private CodeMsg cm;
}
