package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};

/**
* ${table.comment!} 服务类
*
* @Description:
* @Author: ${author}
* @Date: ${.now?string("yyyy-MM-dd HH:mm:ss")}
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
