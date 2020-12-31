package com.chengxiaoxiao.lizhiedu.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 错误码实体
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020-01-08 22:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeMsg implements Serializable {

    private static final long serialVersionUID = 2639752276332232093L;

    private String code;
    private String msg;

    /**
     * 成功的code
     */
    private static String CODE_SUCCESS = "0";

    /**
     * 通用异常
     */
    public static CodeMsg ERROR = new CodeMsg(CODE_SUCCESS, "success");

    /**
     * 用户异常
     */
    public static CodeMsg BIND_ERROR = new CodeMsg("A0001", "参数校验异常：%s");

    public static CodeMsg AUTHENTICATION_TOKEN_EXPIRED = new CodeMsg("A0101", "抱歉，TOKEN已过期");
    public static CodeMsg AUTHENTICATION_ERROR = new CodeMsg("A0102", "抱歉，您没有权限访问本页面");
    public static CodeMsg USER_NOT_LOGIN_ERROR = new CodeMsg("A0103", "抱歉，用户未登录");
    public static CodeMsg LOGIN_SUCCESS = new CodeMsg("A0104", "登录成功");
    public static CodeMsg LOGOUT_SUCCESS = new CodeMsg("A0105", "登出成功");
    public static CodeMsg USER_NOT_EXIST = new CodeMsg("A0106", "用户或者密码错误不存在");
    public static CodeMsg USER_LOCKED = new CodeMsg("A0107", "账户已被冻结，无法登录，如有疑问请联系管理员");
    public static CodeMsg USER_PASSWORD_INCORRENT = new CodeMsg("A0108", "用户名密码不正确");
    public static CodeMsg USER_NAME_EXIST = new CodeMsg("A0109", "用户名信息已存在，请重新输入");


    /**
     * 系统异常
     */
    public static CodeMsg OTHER_ERROR = new CodeMsg("B0001", "系统发生异常：%s");
    public static CodeMsg NOT_PAGE_FOUND = new CodeMsg("B0002", "Page Not Found");

    public static CodeMsg ONLY_ONLY_ERROR = new CodeMsg("B0101", "该数据已经存在");
    public static CodeMsg DB_INSERT_ERROR = new CodeMsg("B0102", "数据库表 添加数据异常：%s");


    /**
     * 第三方错误码
     */

    public static CodeMsg JOBNODE_REQUEST_ERROR = new CodeMsg("C0001", "%s NODE节点错误：%s");


    /**
     * 添加带参数的错误异常
     *
     * @param obj 填充的参数对象
     * @return 填充过后的数据
     */
    public CodeMsg fillArgs(Object... obj) {
        String code = this.code;
        String message = String.format(msg, obj);
        return new CodeMsg(code, message);
    }

}
