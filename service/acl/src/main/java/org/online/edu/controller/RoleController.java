package org.online.edu.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.online.edu.entity.Role;
import org.online.edu.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 */
@RestController
@AllArgsConstructor
@RequestMapping("role")
public class RoleController {

    private RoleService roleService;

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
            Role role) {
        Page<Role> rolePage = new LambdaQueryChainWrapper<>(roleService.getBaseMapper())
                .eq(StringUtils.isNotEmpty(role.getRoleName()), Role::getRoleName, role.getRoleName())
                .page(new Page<>(page, limit));
        return R.ok(rolePage);
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        Role role = roleService.getById(id);
        return R.ok(role);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public R save(@RequestBody Role role) {
        roleService.save(role);
        return R.ok(null).setMsg("保存成功");
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public R updateById(@RequestBody Role role) {
        roleService.updateById(role);
        return R.ok(null).setMsg("修改成功");
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        roleService.removeById(id);
        return R.ok(null).setMsg("删除成功");
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("batchRemove")
    public R batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return R.ok(null).setMsg("删除成功");
    }
}

