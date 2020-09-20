package org.online.edu.controller.front;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Course;
import org.online.edu.entity.Teacher;
import org.online.edu.entity.dto.CourseDto;
import org.online.edu.entity.dto.TeacherDto;
import org.online.edu.entity.vo.TeacherListVo;
import org.online.edu.service.CourseService;
import org.online.edu.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Api(tags = {"讲师前台接口"})
@RequestMapping("teacher-front")
public class TeacherFrontController {

    private TeacherService teacherService;
    private CourseService courseService;

    @ApiOperation(value = "讲师列表")
    @GetMapping("{page}/{limit}")
    public IPage<TeacherDto> page(@PathVariable Integer page, @PathVariable Integer limit) {
        TeacherListVo teacherListVo = new TeacherListVo();
        teacherListVo.setCurrent(page);
        teacherListVo.setLimit(limit);
        IPage pageByParam = teacherService.pageByParam(teacherListVo);
        pageByParam.setRecords((List) pageByParam.getRecords().stream().map(teacher -> BeanUtil.toBean(teacher, TeacherDto.class)).collect(Collectors.toList()));
        return pageByParam;
    }

    @ApiOperation(value = "讲师详情")
    @GetMapping("{id}")
    public Map<String, Object> detail(@PathVariable @ApiParam(name = "id", value = "讲师ID", required = true) String id) {
        Teacher teacher = teacherService.getById(id);
        List<Course> courses = new LambdaQueryChainWrapper<>(courseService.getBaseMapper()).eq(Course::getTeacherId, id).list();
        return new HashMap<String, Object>() {{
            put("teacher", BeanUtil.toBean(teacher, TeacherDto.class));
            put("courses", courses.stream().map(course -> BeanUtil.toBean(course, CourseDto.class)).collect(Collectors.toList()));
        }};
    }
}