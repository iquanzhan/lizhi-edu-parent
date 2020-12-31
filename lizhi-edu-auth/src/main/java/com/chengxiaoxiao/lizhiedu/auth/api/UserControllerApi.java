package com.chengxiaoxiao.lizhiedu.auth.api;


import com.chengxiaoxiao.lizhiedu.auth.dto.query.UserQuerySearch;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserInfo;
import com.chengxiaoxiao.lizhiedu.dto.vo.PageResult;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * 用户接口
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2020/6/1 17:33
 */
@Api(tags = "用户模块", description = "实现对用户数据的基本操作")
public interface UserControllerApi {


    /**
     * 分页及条件查询用户信息
     *
     * @param page 当前页
     * @param size 页码大小
     * @param user 用户条件查询
     * @return 分页用户
     */
    @ApiOperation(value = "分页及条件查询用户信息")
    Result<PageResult<UserInfo>> search(@ApiParam(name = "page", value = "当前页", required = true, type = "path",example = "1", defaultValue = "1") Long page,
                                        @ApiParam(name = "size", value = "页码大小", required = true, type = "path",example = "10", defaultValue = "10") Long size,
                                        UserQuerySearch user);

    /**
     * 根据ID获取用户详情
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @ApiOperation(value = "查看用户详情")
    @ApiParam(name = "id", value = "用户ID", required = true, type = "path")
    Result<UserInfo> detail(String id);

    /**
     * 增加用户
     *
     * @param userInfoSaveModel 用户增加实体
     * @return
     */
    @ApiOperation(value = "增加用户信息")
    Result<UserInfo> add(UserInfo userInfoSaveModel);

    /**
     * 修改用户
     *
     * @param id                用户ID
     * @param userInfoSaveModel 用户修改实体
     * @return
     */
    @ApiOperation(value = "修改用户信息")
    Result<UserInfo> updateById(@ApiParam(name = "id", value = "用户ID", type = "path") String id, UserInfo userInfoSaveModel);

    /**
     * 锁定用户
     *
     * @param id 用户ID
     * @return
     */
    @ApiOperation(value = "根据用户ID冻结用户")
    Result<UserInfo> lockedById(@ApiParam(name = "id", value = "用户ID", type = "path") String id);

    /**
     * 解锁用户
     *
     * @param id 用户ID
     * @return
     */
    @ApiOperation(value = "根据用户ID解锁用户")
    Result<UserInfo> unlockedById(@ApiParam(name = "id", value = "用户ID", type = "path") String id);

    /**
     * 给定用户重置密码
     * @param id 用户ID
     * @param password 用户密码
     * @return 返回用户xinxi
     */
    @ApiOperation(value = "根据用户ID重置用户密码")
    Result<UserInfo> resetPassword(@ApiParam(name = "id", value = "用户ID", type = "path") String id,
                                   @ApiParam(name = "password", value = "用户ID", type = "query") String password);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return
     */
    @ApiOperation(value = "删除单个用户信息")
    Result deleteById(@ApiParam(name = "id", value = "用户ID", type = "path") String id);

    /**
     * 删除用户
     *
     * @param idList 用户ID数组
     * @return
     */
    @ApiOperation(value = "批量删除用户信息")
    Result batchRemove(List<String> idList);

}
