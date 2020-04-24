package org.online.edu.controller;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Permission;
import org.online.edu.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 */
@RestController
@AllArgsConstructor
@RequestMapping("permission")
public class PermissionController {

    private PermissionService permissionService;

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public R indexAllPermission() {
        List<Permission> list = permissionService.queryAllMenu();
        return R.ok(list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        permissionService.removeChildById(id);
        return R.ok(null);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public R doAssign(String roleId, String[] permissionId) {
        permissionService.saveRolePermissionRelationShip(roleId, permissionId);
        return R.ok(null);
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public R toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return R.ok(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public R save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return R.ok(null);
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public R updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return R.ok(null);
    }
}

