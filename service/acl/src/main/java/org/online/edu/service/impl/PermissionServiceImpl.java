package org.online.edu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Permission;
import org.online.edu.entity.RolePermission;
import org.online.edu.entity.User;
import org.online.edu.helper.MemuHelper;
import org.online.edu.helper.PermissionHelper;
import org.online.edu.mapper.PermissionMapper;
import org.online.edu.service.PermissionService;
import org.online.edu.service.RolePermissionService;
import org.online.edu.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 */
@Service
@AllArgsConstructor
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    private RolePermissionService rolePermissionService;
    private UserService userService;

    /**
     * 使用递归方法建菜单
     */
    private static List<Permission> bulid(List<Permission> treeNodes) {
        List<Permission> trees = new ArrayList<>();
        treeNodes.stream().filter(treeNode -> "0".equals(treeNode.getPid())).forEach(treeNode -> {
            treeNode.setLevel(1);
            trees.add(findChildren(treeNode, treeNodes));
        });
        return trees;
    }

    /**
     * 递归查找子节点
     */
    private static Permission findChildren(Permission treeNode, List<Permission> treeNodes) {
        treeNode.setChildren(new ArrayList<>());
        treeNodes.stream().filter(it -> treeNode.getId().equals(it.getPid())).forEach(it -> {
            int level = treeNode.getLevel() + 1;
            it.setLevel(level);
            if (treeNode.getChildren() == null) {
                treeNode.setChildren(new ArrayList<>());
            }
            treeNode.getChildren().add(findChildren(it, treeNodes));
        });
        return treeNode;
    }

    /**
     * 把返回所有菜单list集合进行封装的方法
     */
    public static List<Permission> buildPermission(List<Permission> permissions) {
        //创建list集合，用于数据最终封装
        List<Permission> finalNode = new ArrayList<>();
        //把所有菜单list集合遍历，得到顶层菜单 pid=0菜单，设置level是1
        //得到顶层菜单 pid=0菜单
        //设置顶层菜单的level是1
        //根据顶层菜单，向里面进行查询子菜单，封装到finalNode里面
        permissions.stream().filter(permissionNode -> "0".equals(permissionNode.getPid())).forEach(permissionNode -> {
            permissionNode.setLevel(1);
            finalNode.add(selectChildren(permissionNode, permissions));
        });
        return finalNode;
    }

    private static Permission selectChildren(Permission permissionNode, List<Permission> permissions) {
        //1 因为向一层菜单里面放二层菜单，二层里面还要放三层，把对象初始化
        permissionNode.setChildren(new ArrayList<>());

        //2 遍历所有菜单list集合，进行判断比较，比较id和pid值是否相同
        //判断 id和pid值是否相同
        //把父菜单的level值+1
        //如果children为空，进行初始化操作
        //把查询出来的子菜单放到父菜单里面
        permissions.stream().filter(it -> permissionNode.getId().equals(it.getPid())).forEach(it -> {
            it.setLevel(permissionNode.getLevel() + 1);
            if (permissionNode.getChildren() == null) {
                permissionNode.setChildren(new ArrayList<>());
            }
            permissionNode.getChildren().add(selectChildren(it, permissions));
        });
        return permissionNode;
    }

    /**
     * 根据角色获取菜单
     */
    @Override
    public List<Permission> selectAllMenu(String roleId) {
        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<Permission>().orderByAsc("CAST(id AS SIGNED)"));

        //根据角色id获取角色权限
        List<RolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id", roleId));
        //转换给角色id与角色权限对应Map对象
        allPermissionList.forEach(
                permission -> rolePermissionList.stream().filter(
                        rolePermission -> rolePermission.getPermissionId().equals(permission.getId())
                ).forEach(
                        rolePermission -> permission.setSelect(true)
                ));

        return bulid(allPermissionList);
    }

    /**
     * 根据用户id获取用户菜单
     */
    @Override
    public List<String> selectPermissionValueByUserId(String id) {
        List<String> selectPermissionValueList;
        if (this.isSysAdmin(id)) {
            //如果是系统管理员，获取所有权限
            selectPermissionValueList = baseMapper.selectAllPermissionValue();
        } else {
            selectPermissionValueList = baseMapper.selectPermissionValueByUserId(id);
        }
        return selectPermissionValueList;
    }

    @Override
    public List<JSONObject> selectPermissionByUserId(String userId) {
        List<Permission> selectPermissionList = null;
        if (this.isSysAdmin(userId)) {
            //如果是超级管理员，获取所有菜单
            selectPermissionList = baseMapper.selectList(null);
        } else {
            selectPermissionList = baseMapper.selectPermissionByUserId(userId);
        }

        List<Permission> permissionList = PermissionHelper.bulid(selectPermissionList);
        return MemuHelper.bulid(permissionList);
    }

    /**
     * 判断用户是否系统管理员
     */
    private boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);
        return null != user && "admin".equals(user.getUsername());
    }

    //========================递归查询所有菜单================================================

    /**
     * 获取全部菜单
     */
    @Override
    public List<Permission> queryAllMenu() {
        //1 查询菜单表所有数据
        //2 把查询所有菜单list集合按照要求进行封装
        return buildPermission(new LambdaQueryChainWrapper<>(baseMapper).orderByDesc(Permission::getId).list());
    }

    /**
     * 递归删除菜单
     */
    @Override
    public void removeChildById(String id) {
        //1 创建list集合，用于封装所有删除菜单id值
        List<String> idList = new ArrayList<>();
        //2 向idList集合设置删除菜单id
        this.selectPermissionChildById(id, idList);
        //把当前id封装到list里面
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    /**
     * 根据当前菜单id，查询菜单里面子菜单id，封装到list集合
     */
    private void selectPermissionChildById(String id, List<String> idList) {
        //查询菜单里面子菜单id
        List<Permission> childIds = new LambdaQueryChainWrapper<>(baseMapper)
                .eq(Permission::getPid, id)
                .select(Permission::getId)
                .list();
        //把childIdList里面菜单id值获取出来，封装idList里面，做递归查询
        childIds.forEach(item -> {
            //封装idList里面
            idList.add(item.getId());
            //递归查询
            this.selectPermissionChildById(item.getId(), idList);
        });
    }

    /**
     * 给角色分配菜单
     */
    @Override
    public void saveRolePermissionRelationShip(String roleId, String[] permissionIds) {
        // 删除角色对应菜单
        rolePermissionService.remove(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, roleId));
        //roleId角色id
        //permissionId菜单id 数组形式
        //1 创建list集合，用于封装添加数据
        List<RolePermission> rolePermissions = Arrays.stream(permissionIds).map(permissionId -> new RolePermission().setRoleId(roleId).setPermissionId(permissionId)).collect(Collectors.toList());
        //添加到角色菜单关系表
        rolePermissionService.saveBatch(rolePermissions);
    }
}
