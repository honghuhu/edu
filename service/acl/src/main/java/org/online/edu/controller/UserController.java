package org.online.edu.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.online.edu.entity.User;
import org.online.edu.service.RoleService;
import org.online.edu.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 镜子白
 * @since 2020-01-12
 */
@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象") User userQueryVo) {
        return R.ok(new LambdaQueryChainWrapper<>(userService.getBaseMapper())
                .like(StringUtils.isNotEmpty(userQueryVo.getUsername()), User::getUsername, userQueryVo.getUsername())
                .page(new Page<>(page, limit)));
    }

    @ApiOperation(value = "新增管理用户")
    @GetMapping("{id}")
    public R<User> findById(@PathVariable Long id) {
        return R.ok(userService.getById(id));
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    public R save(@RequestBody User user) {
        user.setPassword(DigestUtil.md5Hex(user.getPassword()));
        userService.save(user);
        return R.ok(null).setMsg("添加成功");
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("update")
    public R updateById(@RequestBody User user) {
        userService.updateById(user);
        return R.ok(null).setMsg("更新成功");
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        userService.removeById(id);
        return R.ok(null).setMsg("删除成功");
    }

    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    public R batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return R.ok(null).setMsg("删除成功");
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public R toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return R.ok(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public R doAssign(@RequestParam String userId, @RequestParam String[] roleId) {
        roleService.saveUserRoleRelationShip(userId, roleId);
        return R.ok(null).setMsg("保存成功");
    }
}

