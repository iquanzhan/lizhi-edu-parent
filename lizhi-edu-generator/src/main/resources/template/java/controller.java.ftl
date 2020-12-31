package ${package.Controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>



/**
 * ${table.comment!} 接口控制器
 *
 * @Description:
 * @Author: ${author}
 * @Date: ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} implements ${table.controllerName}Api{
    <#else>
public class ${table.controllerName} implements ${table.controllerName}Api {
    </#if>
@Resource
private ${entity}Service ${entity?uncap_first}Service;


    @Override
    @GetMapping("/{page}/{size}")
    public Result<PageResult<${entity}>> search(@PathVariable Long page, @PathVariable Long size, ${entity}Query ${entity?uncap_first}Query) {
        if (page <= 0) {
            page = 1L;
        }
        if (size <= 0) {
            size = 10L;
        }
        Page<${entity}> pageParam = new Page<>(page, size);
        QueryWrapper<${entity}> wrapper = new QueryWrapper<>();
    <#list table.fields as field>
        <#if !field.keyIdentityFlag && field.propertyName!='id' && field.propertyName!='version' && field.propertyName!='createTime' && field.propertyName!='updateTime' && field.propertyName!='deleteStatus'>
        <#if field.propertyType=='String'>
         if (!StringUtils.isEmpty(${entity?uncap_first}Query.get${field.propertyName?cap_first}())) {
            wrapper.like("${field.name}", ${entity?uncap_first}Query.get${field.propertyName?cap_first}());
         }
        </#if>
        <#if field.propertyType=='Integer'>
        if (${entity?uncap_first}Query.get${field.propertyName?cap_first}()!=null) {
            wrapper.eq("${field.name}", ${entity?uncap_first}Query.get${field.propertyName?cap_first}());
        }
        </#if>
        </#if>
    </#list>
        ${entity?uncap_first}Service.page(pageParam, wrapper);
        PageResult<${entity}> pageResult = new PageResult<${entity}>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    @Override
    @GetMapping("/{id}")
    public Result<${entity}> detail(@PathVariable String id) {
        return Result.success(${entity?uncap_first}Service.getById(id));
    }

    @Override
    @PostMapping
    public Result<${entity}> add(@RequestBody @Validated(AddGroup.class) ${entity} ${entity?uncap_first}) {
        ${entity?uncap_first}Service.save(${entity?uncap_first});
        return Result.success(${entity?uncap_first});
    }

    @Override
    @PutMapping("/{id}")
    public Result<${entity}> updateById(@PathVariable String id,@RequestBody @Validated(UpdateGroup.class) ${entity} ${entity?uncap_first}) {
        ${entity?uncap_first}.setId(id);
        ${entity?uncap_first}Service.updateById(${entity?uncap_first});
        return Result.success(${entity?uncap_first}Service.getById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable String id) {
        ${entity?uncap_first}Service.removeById(id);
        return Result.success(true);
    }

    @Override
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@RequestBody List<String> idList) {
        ${entity?uncap_first}Service.removeByIds(idList);
        return Result.success(true);
    }
}
</#if>
