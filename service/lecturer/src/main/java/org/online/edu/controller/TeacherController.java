package org.online.edu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Teacher;
import org.online.edu.entity.dto.TeacherDto;
import org.online.edu.entity.vo.TeacherListVo;
import org.online.edu.entity.vo.TeacherVo;
import org.online.edu.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-03-28
 */
@RestController
@AllArgsConstructor
@Api(tags = {"讲师管理"})
@RequestMapping("teacher")
public class TeacherController {

    private TeacherService teacherService;

    @ApiOperation(value = "讲师列表")
    @PostMapping("page")
    public R<IPage<TeacherDto>> page(@RequestBody TeacherListVo teacherListVo) {
        IPage page = teacherService.pageByParam(teacherListVo);
        page.setRecords((List) page.getRecords().stream().map(teacher -> BeanUtil.toBean(teacher, TeacherDto.class)).collect(Collectors.toList()));
        return R.ok(page);
    }

    @ApiOperation(value = "讲师列表")
    @GetMapping("all")
    public R<List<TeacherDto>> all() {
        List<Teacher> teachers = teacherService.list(new QueryWrapper<>());
        return R.ok(teachers.stream().map(teacher -> BeanUtil.toBean(teacher, TeacherDto.class)).collect(Collectors.toList()));
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("save")
    public R<Boolean> save(@Valid @RequestBody TeacherVo teacherVo) {
        teacherService.save(BeanUtil.toBean(teacherVo, Teacher.class));
        return R.ok(true);
    }

    @ApiOperation(value = "更新讲师")
    @PostMapping("modify/{id}")
    public R<Boolean> modify(@PathVariable @ApiParam(value = "讲师主键id", required = true) String id, @Valid @RequestBody TeacherVo teacherVo) {
        Teacher entity = BeanUtil.toBean(teacherVo, Teacher.class);
        entity.setId(id);
        if (teacherService.updateById(entity)) {
            return R.ok(true).setMsg("修改成功");
        }
        return R.failed("修改失败");
    }

    @ApiOperation(value = "逻辑删除")
    @DeleteMapping("{id}")
    public R<Boolean> deleteById(@PathVariable @ApiParam(value = "讲师id", required = true) Long id) {
        if (teacherService.removeById(id)) {
            return R.ok(true).setMsg("删除成功");
        }
        return R.failed("删除失败");
    }
}