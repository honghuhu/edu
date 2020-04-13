package org.online.edu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Course;
import org.online.edu.entity.dto.CourseDto;
import org.online.edu.entity.dto.CourseInfoDto;
import org.online.edu.entity.vo.CourseInfoVo;
import org.online.edu.entity.vo.CourseListVo;
import org.online.edu.entity.vo.CoursePublishVo;
import org.online.edu.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("course")
public class CourseController {

    private CourseService courseService;

    @PostMapping("save")
    @ApiOperation("添加课程")
    public R<HashMap<String, String>> save(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok(new HashMap<String, String>(1) {{
            put("id", id);
        }});
    }

    @PostMapping("modify/{id}")
    public R<String> modify(@PathVariable String id, @RequestBody CourseInfoVo courseInfoVo) {
        courseService.modifyCourseInfo(id, courseInfoVo);
        return R.ok("更新成功");
    }

    @GetMapping("{id}")
    public R<CourseInfoDto> findById(@PathVariable String id) {
        return R.ok(courseService.findById(id));
    }

    @GetMapping("publish/{id}")
    public R<CoursePublishVo> findCoursePublishVoById(@PathVariable String id) {
        return R.ok(courseService.findCoursePublishVoById(id));
    }

    @PostMapping("publish/{id}")
    public R<Boolean> publishCourse(@PathVariable String id) {
        return R.ok(courseService.update(new LambdaUpdateWrapper<Course>().eq(Course::getId, id).set(Course::getStatus, "Normal")));
    }

    @PostMapping("page")
    public R<IPage<CourseDto>> page(@RequestBody CourseListVo courseListVo) {
        IPage page = courseService.pageByParam(courseListVo);
        page.setRecords((List) page.getRecords().stream().map(course -> BeanUtil.toBean(course, CourseDto.class)).collect(Collectors.toList()));
        return R.ok(page);
    }

    @DeleteMapping("{id}")
    public R<Boolean> remove(@PathVariable String id) {
        return R.ok(courseService.removeById(id));
    }
}

