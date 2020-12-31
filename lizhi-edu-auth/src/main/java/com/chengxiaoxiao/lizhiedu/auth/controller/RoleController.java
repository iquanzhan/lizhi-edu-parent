package com.chengxiaoxiao.lizhiedu.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengxiaoxiao.lizhiedu.auth.api.RoleControllerApi;
import com.chengxiaoxiao.lizhiedu.auth.dto.query.RoleQuerySearch;
import com.chengxiaoxiao.lizhiedu.auth.entity.RoleInfo;
import com.chengxiaoxiao.lizhiedu.auth.service.RoleService;
import com.chengxiaoxiao.lizhiedu.dto.vo.PageResult;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色接口
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@RestController
@RequestMapping("/role")
public class RoleController implements RoleControllerApi {

    @Autowired
    private RoleService roleService;


    @Override
    @GetMapping("/{page}/{size}")
    public Result<PageResult<RoleInfo>> search(@PathVariable Long page, @PathVariable Long size, RoleQuerySearch roleQuery) {
        Page<RoleInfo> pageParam = new Page<>(page, size);
        QueryWrapper<RoleInfo> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        if (!StringUtils.isEmpty(roleQuery.getRoleName())) {
            wrapper.like("role_name", roleQuery.getRoleName());
        }
        if (!StringUtils.isEmpty(roleQuery.getRoleCode())) {
            wrapper.like("key", roleQuery.getRoleCode());
        }
        roleService.page(pageParam, wrapper);
        PageResult<RoleInfo> pageResult = new PageResult<RoleInfo>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    @Override
    @GetMapping
    public Result<List<RoleInfo>> list() {
        return Result.success(roleService.list());
    }

    @Override
    @GetMapping("/{id}")
    public Result<RoleInfo> detail(@PathVariable String id) {
        return Result.success(roleService.getById(id));
    }

    @Override
    @PostMapping
    public Result<RoleInfo> add(@RequestBody @Valid RoleInfo role) {
        roleService.save(role);
        return Result.success(role);
    }

    @Override
    @PutMapping("/{id}")
    public Result updateById(@PathVariable String id, @RequestBody @Valid RoleInfo role) {
        role.setId(id);
        roleService.updateById(role);
        return Result.success(roleService.getById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        roleService.removeById(id);
        return Result.success(true);
    }

    @Override
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return Result.success(true);
    }


}

