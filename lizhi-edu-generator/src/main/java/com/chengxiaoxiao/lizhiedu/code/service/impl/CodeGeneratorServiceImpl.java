package com.chengxiaoxiao.lizhiedu.code.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.chengxiaoxiao.lizhiedu.code.service.CodeGeneratorService;
import com.chengxiaoxiao.lizhiedu.code.config.FileProperties;
import com.chengxiaoxiao.lizhiedu.code.util.FileUtils;
import com.chengxiaoxiao.lizhiedu.code.vo.query.DatabaseConfigQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器业务逻辑层实现类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/13 16:09
 * @Version 1.0
 */
@Service
public class CodeGeneratorServiceImpl implements CodeGeneratorService {

    @Resource
    private HttpServletRequest request;
    @Resource
    FileProperties fileProperties;


    @Override
    public String generateFrontEnd(DatabaseConfigQuery query) {
        String relPath = FileUtils.generateRealFilePath();
        String fileDir = fileProperties.getUploadPath() + "/" + relPath;

        if (!FileUtil.exist(fileDir)) {
            FileUtil.mkdir(fileDir);
        }


        return generateCodeFile(query, fileDir, false);
    }

    /**
     * 中划线
     *
     * @param name
     * @return
     */
    static String centerStr(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (Character.isUpperCase(ch)) {
                stringBuilder.append("-" + (ch + "").toLowerCase());
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String generateBackEnd(DatabaseConfigQuery query) {
        String relPath = FileUtils.generateRealFilePath();
        String fileDir = fileProperties.getUploadPath() + "/" + relPath;

        if (!FileUtil.exist(fileDir)) {
            FileUtil.mkdir(fileDir);
        }

        return generateCodeFile(query, fileDir, true);
    }


    public String generateCodeFile(DatabaseConfigQuery query, String fileDir, boolean isBackEnd) {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();

        gc.setOutputDir(fileDir + (isBackEnd ? "/src/main/java" : ""));

        gc.setAuthor(query.getAuthor());
        gc.setOpen(false);
        gc.setFileOverride(false);

        if (query.getIsServiceFirstLetter()) {
            //去掉Service接口的首字母I
            gc.setServiceName("%sService");
        }


        IdType type = IdType.AUTO;
        if (query.getIdType().equals(1)) {
            type = IdType.NONE;
        } else if (query.getIdType().equals(2)) {
            type = IdType.INPUT;
        } else if (query.getIdType().equals(3)) {
            type = IdType.ID_WORKER;
        } else if (query.getIdType().equals(4)) {
            type = IdType.UUID;
        } else if (query.getIdType().equals(5)) {
            type = IdType.ID_WORKER_STR;
        }
        //主键策略
        gc.setIdType(type);


        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(query.getIsSwagger());

        mpg.setGlobalConfig(gc);


        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:dm://" + query.getDbIp() + ":" + query.getDbPort() + "/" + query.getDbSchema());
        dsc.setDriverName("dm.jdbc.driver.DmDriver");
        dsc.setSchemaName(query.getDbSchema());
        dsc.setUsername(query.getDbUserName());
        dsc.setPassword(query.getDbPassword());
        dsc.setDbType(DbType.ORACLE);
        dsc.setTypeConvert(new OracleTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if ("int".equals(fieldType.toLowerCase())) {
                    return DbColumnType.INTEGER;
                }
                if ("integer".equals(fieldType.toLowerCase())) {
                    return DbColumnType.INTEGER;
                }
                if ("tinyint".equals(fieldType.toLowerCase())) {
                    return DbColumnType.BOOLEAN;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);


        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(query.getModuleName());
        pc.setParent(query.getParentPackage());
        pc.setController(query.getControllerName());
        pc.setEntity(query.getEntityName());
        pc.setService(query.getServiceName());
        pc.setMapper(query.getMapperName());
        mpg.setPackageInfo(pc);


        // 4.1 模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        if (isBackEnd) {
            templateConfig.setController("template/java/controller.java");
            templateConfig.setXml(null);
            templateConfig.setEntity("template/java/entity.java");
            templateConfig.setEntityKt(null);
            templateConfig.setMapper("template/java/mapper.java");
            templateConfig.setService("template/java/service.java");
            templateConfig.setServiceImpl("template/java/serviceImpl.java");
        } else {
            templateConfig.setXml(null);
            templateConfig.setController(null);

            templateConfig.setEntity(null);
            templateConfig.setEntityKt(null);
            templateConfig.setMapper(null);
            templateConfig.setService(null);
            templateConfig.setServiceImpl(null);
        }


        mpg.setTemplate(templateConfig);

        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                this.setMap(getMap());
            }
        };


        List<FileOutConfig> foc = new ArrayList<>();

        if (isBackEnd) {

            //后端文件生成

            /*
              MyBatis Xml
             */
            foc.add(new FileOutConfig("template/java/mapper.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return StrUtil.appendIfMissing(fileDir, "/") + "/src/main/resources/mapping/" + tableInfo.getEntityName() + "Mapper"
                            + StringPool.DOT_XML;
                }
            });


            /**
             * java class中的api
             */
            foc.add(new FileOutConfig("template/java/api.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return StrUtil.appendIfMissing(gc.getOutputDir(), "/") + pc.getParent().replace(".", "/") + "/api/" + tableInfo.getEntityName() + "ControllerApi.java";
                }
            });

            /**
             * 生成查询的Query
             */
            foc.add(new FileOutConfig("template/java/query.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return StrUtil.appendIfMissing(gc.getOutputDir(), "/") + pc.getParent().replace(".", "/") + "/vo/query/" + tableInfo.getEntityName() + "Query.java";
                }
            });
        } else {
            //前端文件的生成
            /**
             * vue中的页面展示
             */
            foc.add(new FileOutConfig("template/vue/view.vue.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return StrUtil.appendIfMissing(gc.getOutputDir(), "/") + "/views/" + centerStr(StrUtil.lowerFirst(tableInfo.getEntityName())) + "/index.vue";
                }
            });

            foc.add(new FileOutConfig("template/vue/api.vue.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return StrUtil.appendIfMissing(gc.getOutputDir(), "/") + "/api/" + centerStr(StrUtil.lowerFirst(tableInfo.getEntityName())) + ".js";
                }
            });
        }


        ic.setFileOutConfigList(foc);

        mpg.setCfg(ic);


        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();

        strategy.setInclude(query.getTableName().split(","));

        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //生成实体时去掉表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");

        //数据库表字段映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(query.getIsLombok());

        strategy.setRestControllerStyle(true);
        strategy.setVersionFieldName("VERSION");
        //填充字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("CREATE_TIME", FieldFill.INSERT));
        tableFillList.add(new TableFill("UPDATE_TIME", FieldFill.INSERT_UPDATE));

        strategy.setTableFillList(tableFillList);

        //url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());


        mpg.execute();


        if (!isBackEnd) {
            //前端复制其他内容
            File[] fileList = FileUtil.ls(fileProperties.getUploadPath() + "/template/vue");

            for (File file : fileList) {
                FileUtil.copy(file.getAbsolutePath(), fileDir, true);
            }
        }

        //打包输出
        String moduleZipFile = FileUtil.getParent(fileDir, 1) + "/" + query.getModuleName() + ".zip";
        ZipUtil.zip(fileDir, moduleZipFile);

        return fileProperties.getAbsUploadPath(moduleZipFile);
    }
}
