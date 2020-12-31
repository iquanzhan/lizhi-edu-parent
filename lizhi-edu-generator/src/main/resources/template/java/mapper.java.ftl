package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
* ${table.comment!} Mapper 接口
*
* @Description:
* @Author: ${author}
* @Date: ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>
