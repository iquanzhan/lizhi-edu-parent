package com.chengxiaoxiao.lizhiedu.code.vo.query;

import lombok.Data;

/**
 * 数据库配置对象
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/13 16:05
 * @Version 1.0
 */
@Data
public class DatabaseConfigQuery {
    private String author;
    private Integer dbType;
    private Integer idType;
    private String mapperName;
    private String controllerName;
    private Boolean isLombok;
    private Boolean isRestful;
    private String entityName;
    private String serviceName;
    private Boolean isSwagger;
    private Boolean isServiceFirstLetter;
    private String dbIp;
    private String dbSchema;
    private String dbUserName;
    private String dbPassword;
    private String dbPort;
    private String moduleName;
    private String parentPackage;
    private String tableName;
}
