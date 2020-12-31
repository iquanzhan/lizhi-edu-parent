package ${package.parent}.${package.ModuleName}.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * ${table.comment?substring(0,table.comment?length-1)}接口Swagger Api
 *
 * @Description:
 * @Author: ${author}
 * @Date: ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Api(tags = "${table.comment?substring(0,table.comment?length-1)}模块", description = "实现对${table.comment?substring(0,table.comment?length-1)}数据的基本操作")
public interface ${entity}ControllerApi {


    @ApiOperation(value = "分页及条件查询${table.comment?substring(0,table.comment?length-1)}信息")
    Result<PageResult<${entity}>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path") Long page,
                                              @ApiParam(name = "size", value = "页码大小", required = true, type = "path") Long size,
                                              ${entity}Query ${entity?uncap_first});

    /**
     * 根据ID获取${table.comment?substring(0,table.comment?length-1)}详情
     *
     * @param id ${table.comment?substring(0,table.comment?length-1)}ID
     * @return
     */
    @ApiOperation(value = "查看${table.comment?substring(0,table.comment?length-1)}详情")
    @ApiParam(name = "id", value = "${table.comment?substring(0,table.comment?length-1)}ID", required = true, type = "path")
    Result<${entity}> detail(String id);

    /**
     * 增加${table.comment?substring(0,table.comment?length-1)}
     *
     * @param ${entity?uncap_first} ${table.comment?substring(0,table.comment?length-1)}增加实体
     * @return
     */
    @ApiOperation(value = "增加${table.comment?substring(0,table.comment?length-1)}信息")
    Result<${entity}> add(${entity} ${entity?uncap_first});

    /**
     * 修改${table.comment?substring(0,table.comment?length-1)}
     *
     * @param id             ${table.comment?substring(0,table.comment?length-1)}ID
     * @param ${entity?uncap_first} ${table.comment?substring(0,table.comment?length-1)}修改实体
     * @return
     */
    @ApiOperation(value = "修改${table.comment?substring(0,table.comment?length-1)}信息")
    Result<${entity}> updateById(@ApiParam(name = "id", value = "${table.comment?substring(0,table.comment?length-1)}ID", type = "path") String id, ${entity} ${entity?uncap_first});

    /**
     * 删除${table.comment?substring(0,table.comment?length-1)}
     *
     * @param id ${table.comment?substring(0,table.comment?length-1)}ID
     * @return
     */
    @ApiOperation(value = "删除单个${table.comment?substring(0,table.comment?length-1)}信息")
    Result<Boolean> deleteById(@ApiParam(name = "id", value = "${table.comment?substring(0,table.comment?length-1)}ID", type = "path") String id);

    /**
     * 删除${table.comment?substring(0,table.comment?length-1)}
     *
     * @param idList ${table.comment?substring(0,table.comment?length-1)}ID数组
     * @return
     */
    @ApiOperation(value = "批量删除${table.comment?substring(0,table.comment?length-1)}信息")
    Result<Boolean> batchRemove(List<String> idList);

}
