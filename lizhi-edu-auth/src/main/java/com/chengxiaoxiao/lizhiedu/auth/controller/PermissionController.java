package com.chengxiaoxiao.lizhiedu.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengxiaoxiao.lizhiedu.auth.api.PermissionControllerApi;
import com.chengxiaoxiao.lizhiedu.auth.dto.query.PermissionQuerySearch;
import com.chengxiaoxiao.lizhiedu.auth.dto.vo.PermissionTree;
import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;
import com.chengxiaoxiao.lizhiedu.auth.service.PermissionService;
import com.chengxiaoxiao.lizhiedu.dto.entity.LoginUser;
import com.chengxiaoxiao.lizhiedu.dto.vo.PageResult;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 权限接口
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@RestController
@RequestMapping("/permission")
public class PermissionController implements PermissionControllerApi {

    @Autowired
    private PermissionService permissionService;

    @Override
    @GetMapping("/{page}/{size}")
    public Result<PageResult<Permission>> search(@PathVariable Long page, @PathVariable Long size, PermissionQuerySearch permissionQuery) {
        Page<Permission> pageParam = new Page<>(page, size);
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        if (!StringUtils.isEmpty(permissionQuery.getName())) {
            wrapper.like("name", permissionQuery.getName());
        }
        if (!StringUtils.isEmpty(permissionQuery.getKey())) {
            wrapper.like("key", permissionQuery.getKey().toUpperCase());
        }
        if (null != permissionQuery.getType()) {
            wrapper.eq("type", permissionQuery.getType());
        }

        permissionService.page(pageParam, wrapper);
        PageResult<Permission> pageResult = new PageResult<Permission>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    @Override
    @GetMapping("/{id}")
    public Result<Permission> detail(@PathVariable String id) {
        return Result.success(permissionService.getById(id));
    }

    @Override
    @PostMapping
    public Result<Permission> add(@RequestBody @Valid Permission permission) {
        permission.setKey(permission.getKey().toUpperCase());
        permissionService.save(permission);
        return Result.success(permission);
    }

    @Override
    @PutMapping("/{id}")
    public Result updateById(@PathVariable String id, @RequestBody Permission permission) {

        permission.setId(id);
        permission.setKey(permission.getKey().toUpperCase());
        permissionService.updateById(permission);
        return Result.success(permissionService.getById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {

        permissionService.deleteAll(id);
        return Result.success(true);
    }

    @Override
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        permissionService.deleteByIds(idList);
        return Result.success(true);
    }

    @Override
    @GetMapping("/tree")
    public Result<List<PermissionTree>> tree() {
        return Result.success(permissionService.tree());
    }

    @Override
    @GetMapping("/application")
    public Result<List<Permission>> getApplication(LoginUser user) {
        return Result.success(permissionService.getApplicationList(user.getId()));
    }

}

