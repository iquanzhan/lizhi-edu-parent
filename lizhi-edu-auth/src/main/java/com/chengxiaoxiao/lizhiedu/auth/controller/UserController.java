package com.chengxiaoxiao.lizhiedu.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengxiaoxiao.lizhiedu.auth.api.UserControllerApi;
import com.chengxiaoxiao.lizhiedu.auth.dto.query.UserQuerySearch;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserInfo;
import com.chengxiaoxiao.lizhiedu.auth.service.UserService;
import com.chengxiaoxiao.lizhiedu.dto.vo.PageResult;
import com.chengxiaoxiao.lizhiedu.dto.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@RestController
@RequestMapping("user")
public class UserController implements UserControllerApi {
    @Autowired
    private UserService userService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @GetMapping("/{page}/{size}")
    public Result<PageResult<UserInfo>> search(@PathVariable Long page, @PathVariable Long size, UserQuerySearch user) {
        Page<UserInfo> pageParam = new Page<>(page, size);
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        if (!StringUtils.isEmpty(user.getUsername())) {
            wrapper.like("user_name", user.getUsername());
        }
        if (!StringUtils.isEmpty(user.getNickName())) {
            wrapper.like("nick_name", user.getNickName());
        }
        userService.page(pageParam, wrapper);
        PageResult<UserInfo> pageResult = new PageResult<UserInfo>(pageParam.getTotal(), pageParam.getSize(), pageParam.getCurrent(), pageParam.getRecords());

        return Result.success(pageResult);
    }

    @Override
    @GetMapping("/{id}")
    public Result<UserInfo> detail(@PathVariable String id) {
        return Result.success(userService.getById(id));
    }

    @Override
    @PostMapping
    public Result<UserInfo> add(@RequestBody @Valid UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userService.addUser(userInfo);
        return Result.success(userInfo);
    }

    @Override
    @PutMapping("/{id}")
    public Result updateById(@PathVariable String id, @RequestBody @Valid UserInfo userInfo) {
        userInfo.setId(id);
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userService.updateById(userInfo);
        return Result.success(userService.getById(id));
    }

    @Override
    @PutMapping("/locked/{id}")
    public Result<UserInfo> lockedById(@PathVariable String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setLocked(1);
        userService.updateById(userInfo);
        return Result.success(userInfo);
    }

    @Override
    @PutMapping("/unlocked/{id}")
    public Result<UserInfo> unlockedById(@PathVariable String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setLocked(0);
        userService.updateById(userInfo);
        return Result.success(userInfo);
    }

    @Override
    @PutMapping("/password/{id}")
    public Result<UserInfo> resetPassword(@PathVariable String id, @RequestBody String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setPassword(bCryptPasswordEncoder.encode(password));
        userService.updateById(userInfo);
        return Result.success(userInfo);
    }

    @Override
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        userService.removeById(id);
        return Result.success(true);
    }

    @Override
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return Result.success(true);
    }

}

