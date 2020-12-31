package com.chengxiaoxiao.lizhiedu.auth.api;


import com.chengxiaoxiao.lizhiedu.auth.dto.query.PermissionQuerySearch;
import com.chengxiaoxiao.lizhiedu.auth.dto.vo.PermissionTree;
import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;
import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import com.chengxiaoxiao.lizhiedu.dto.vo.PageResult;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * 权限接口
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/3 14:57
 */
@Api(tags = "权限模块", description = "实现对权限数据的基本操作")
public interface PermissionControllerApi {

    /**
     * 分页及条件查询
     *
     * @param page      当前页
     * @param size      页码大小
     * @param roleQuery 条件查询条件
     * @return 分页数据
     */
    @ApiOperation(value = "分页及条件查询权限信息")
    Result<PageResult<Permission>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path",example = "1", defaultValue = "1") Long page,
                                          @ApiParam(name = "size", value = "页码大小", required = true, type = "path",example = "10", defaultValue = "10") Long size,
                                          PermissionQuerySearch roleQuery);

    /**
     * 根据ID获取权限详情
     *
     * @param id 权限ID
     * @return
     */
    @ApiOperation(value = "查看权限详情")
    @ApiParam(name = "id", value = "权限ID", required = true, type = "path")
    Result<Permission> detail(String id);

    /**
     * 增加权限
     *
     * @param permission 权限增加实体
     * @return
     */
    @ApiOperation(value = "增加权限信息")
    Result<Permission> add(Permission permission);

    /**
     * 修改权限
     *
     * @param id         权限ID
     * @param permission 权限修改实体
     * @return
     */
    @ApiOperation(value = "修改权限信息")
    Result<Permission> updateById(@ApiParam(name = "id", value = "权限ID", type = "path") String id, Permission permission);

    /**
     * 删除权限
     *
     * @param id 权限ID
     * @return
     */
    @ApiOperation(value = "删除单个权限信息")
    Result deleteById(@ApiParam(name = "id", value = "权限ID", type = "path") String id);

    /**
     * 删除权限
     *
     * @param idList 权限ID数组
     * @return
     */
    @ApiOperation(value = "批量删除权限信息")
    Result batchRemove(List<String> idList);

    /**
     * 查询树形结构
     *
     * @return
     */
    @ApiOperation(value = "查询树形权限")
    Result<List<PermissionTree>> tree();


    /**
     * 获取拥有权限的应用列表
     *
     * @param user 登录的用户信息
     * @return 用户应用列表
     */
    @ApiOperation(value = "获取拥有权限的应用列表")
    Result<List<Permission>> getApplication(LoginUser user);

}
