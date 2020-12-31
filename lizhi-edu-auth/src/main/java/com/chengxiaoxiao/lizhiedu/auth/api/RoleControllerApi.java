package com.chengxiaoxiao.lizhiedu.auth.api;

import com.chengxiaoxiao.lizhiedu.auth.dto.query.RoleQuerySearch;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;
import com.chengxiaoxiao.lizhiedu.dto.vo.PageResult;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * 角色接口
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/3 14:57
 */
@Api(tags = "角色模块", description = "实现对角色数据的基本操作")
public interface RoleControllerApi {

    /**
     * 分页及条件查询角色信息
     *
     * @param page      当前页
     * @param size      页码大小
     * @param roleQuery 条件查询条件
     * @return 角色分页数据
     */
    @ApiOperation(value = "分页及条件查询角色信息")
    Result<PageResult<RoleInfo>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path",example = "1", defaultValue = "1") Long page,
                                        @ApiParam(name = "size", value = "页码大小", required = true, type = "path",example = "10", defaultValue = "10") Long size,
                                        RoleQuerySearch roleQuery);

    /**
     * 查询角色列表
     *
     * @return
     */
    @ApiOperation(value = "查看角色列表")
    Result<List<RoleInfo>> list();

    /**
     * 根据ID获取角色详情
     *
     * @param id 角色ID
     * @return
     */
    @ApiOperation(value = "查看角色详情")
    @ApiParam(name = "id", value = "角色ID", required = true, type = "path")
    Result<RoleInfo> detail(String id);

    /**
     * 增加角色
     *
     * @param role 角色信息
     * @return
     */
    @ApiOperation(value = "增加角色信息")
    Result<RoleInfo> add(RoleInfo role);

    /**
     * 修改角色
     *
     * @param id   角色ID
     * @param role 角色信息
     * @return
     */
    @ApiOperation(value = "修改角色信息")
    Result<RoleInfo> updateById(@ApiParam(name = "id", value = "角色ID", type = "path") String id, RoleInfo role);

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return
     */
    @ApiOperation(value = "删除单个角色信息")
    Result deleteById(@ApiParam(name = "id", value = "角色ID", type = "path") String id);

    /**
     * 删除角色
     *
     * @param idList 角色ID数组
     * @return
     */
    @ApiOperation(value = "批量删除角色信息")
    Result batchRemove(List<String> idList);

}
