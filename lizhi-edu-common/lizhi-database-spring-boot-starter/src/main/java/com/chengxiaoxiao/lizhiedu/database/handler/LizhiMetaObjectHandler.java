package com.chengxiaoxiao.lizhiedu.database.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.chengxiaoxiao.lizhiedu.database.config.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * 字段自定义填充设置
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2020/11/4 10:26
 * @Version 1.0
 */
@Slf4j
public class LizhiMetaObjectHandler implements MetaObjectHandler {

    @Resource
    private DatabaseProperties databaseProperties;

    /**
     * 使用mp实现添加操作，这个方法执行
     *
     * @param metaObject 填充的对象类型
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //1.判断是否开启2.判断对象是否存在此Setter3.判断用户是否已经设置
        if (databaseProperties.getConfig().getEnableLogicDelete()) {
            String logicDeleteField = databaseProperties.getConfig().getLogicDeleteField();
            if (metaObject.hasSetter(logicDeleteField) && Objects.isNull(this.getFieldValByName(logicDeleteField, metaObject))) {
                this.setInsertFieldValByName(
                        logicDeleteField,
                        Integer.parseInt(databaseProperties.getConfig().getLogicNotDeleteValue()),
                        metaObject
                );
            }
        }

        if (databaseProperties.getConfig().getEnableCreateTime()) {
            String createTimeField = databaseProperties.getConfig().getCreateTimeField();
            if (metaObject.hasSetter(createTimeField) && Objects.isNull(this.getFieldValByName(createTimeField, metaObject))) {
                this.setInsertFieldValByName(createTimeField, new Date(), metaObject);
            }
        }


        if (databaseProperties.getConfig().getEnableUpdateTime()) {
            String updateTimeField = databaseProperties.getConfig().getUpdateTimeField();
            if (metaObject.hasSetter(updateTimeField) && Objects.isNull(this.getFieldValByName(updateTimeField, metaObject))) {
                this.setInsertFieldValByName(updateTimeField, new Date(), metaObject);
            }
        }


        if (databaseProperties.getConfig().getEnableVersion()) {
            String versionField = databaseProperties.getConfig().getVersionField();
            if (metaObject.hasSetter(versionField) && Objects.isNull(this.getFieldValByName(versionField, metaObject))) {
                this.setInsertFieldValByName(versionField, 1, metaObject);
            }
        }
    }

    /**
     * 使用mp实现修改操作，这个方法执行
     *
     * @param metaObject 填充对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (databaseProperties.getConfig().getEnableUpdateTime()) {
            String updateTimeField = databaseProperties.getConfig().getUpdateTimeField();
            if (metaObject.hasSetter(updateTimeField) && Objects.isNull(this.getFieldValByName(updateTimeField, metaObject))) {
                this.setUpdateFieldValByName(updateTimeField, new Date(), metaObject);
            }
        }
    }
}
