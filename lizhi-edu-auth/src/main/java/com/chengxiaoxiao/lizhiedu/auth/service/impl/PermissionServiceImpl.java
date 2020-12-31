package com.chengxiaoxiao.lizhiedu.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chengxiaoxiao.lizhiedu.auth.dto.vo.PermissionTree;
import com.chengxiaoxiao.lizhiedu.auth.entity.Permission;
import com.chengxiaoxiao.lizhiedu.auth.entity.UserInfo;
import com.chengxiaoxiao.lizhiedu.auth.mapper.PermissionMapper;
import com.chengxiaoxiao.lizhiedu.auth.service.PermissionService;
import com.chengxiaoxiao.lizhiedu.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author Cheng xiaoxiao
 * @since 2020-06-01
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    public static final String ADMIN = "admin";
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public List<String> selectPermissionValueByUserId(String userId) {

        List<String> selectPermissionValueList = null;
        if (this.isSysAdmin(userId)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(userId);
        }
        return selectPermissionValueList;
    }

    @Override
    public void deleteAll(String id) {
        deleteId(id);
    }

    @Override
    public List<Permission> getApplicationList(String userId) {
        List<Permission> permissionByUserIdType = permissionMapper.getPermissionByUserIdType(userId, 0);
        List<Permission> list = new ArrayList<>(permissionByUserIdType.size());
        for (Permission permission : permissionByUserIdType) {
//            if (FileTypeUtil.isImageType(permission.getIcon())) {
//                permission.setIcon(fileServerProperty.getUrl() + permission.getIcon());
//            }
            list.add(permission);
        }
        return list;
    }

    @Override
    public void deleteByIds(List<String> idList) {
        for (String id : idList) {
            deleteAll(id);
        }
    }

    void deleteId(String id) {
        List<Permission> allPermission = baseMapper.selectList(new QueryWrapper<Permission>().eq("pid", id).orderByAsc("id"));
        baseMapper.deleteById(id);

        for (Permission permission : allPermission) {
            baseMapper.deleteById(permission.getId());
            this.deleteId(permission.getId());
        }
    }


    //===================================递归查询所有菜单=====================================================================

    @Override
    public List<PermissionTree> tree() {

        //查询所有
        List<Permission> allPermission = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("id"));
        List<PermissionTree> list = new ArrayList<>();

        //进行递归查询，封装成树结构
        for (Permission permission : allPermission) {
            if ("0".equals(permission.getPid())) {
                //存在多个一级节点
                list.add(treeSelectChildren(permission, allPermission));
            }
        }

        return list;
    }

    /**
     * 递归查询孩子节点
     *
     * @param permission    父节点
     * @param allPermission 所有权限
     * @return
     */
    private PermissionTree treeSelectChildren(Permission permission, List<Permission> allPermission) {
        PermissionTree permissionTree = new PermissionTree();
        BeanUtil.copyProperties(permission, permissionTree);
        String icon = permissionTree.getIcon();

//        if (FileTypeUtil.isImageType(permissionTree.getIcon())) {
//            permissionTree.setIcon(fileServerProperty.getUrl() + permissionTree.getIcon());
//        }

        if (permissionTree.getChildren() == null) {
            permissionTree.setChildren(new ArrayList<PermissionTree>());
        }

        for (Permission item : allPermission) {
            if (item.getPid().equals(permission.getId())) {
                permissionTree.getChildren().add(treeSelectChildren(item, allPermission));
            }
        }
        return permissionTree;
    }

    //=================================================递归查询菜单=======================================================================

    /**
     * 判断用户是否系统管理员
     *
     * @param userId
     * @return
     */
    private boolean isSysAdmin(String userId) {
        UserInfo userInfo = userService.getById(userId);

        if (null != userInfo && ADMIN.equals(userInfo.getUserName())) {
            return true;
        }
        return false;
    }
}
