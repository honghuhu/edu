package org.online.edu.controller.front;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Course;
import org.online.edu.entity.Teacher;
import org.online.edu.entity.dto.CourseDto;
import org.online.edu.entity.dto.TeacherDto;
import org.online.edu.service.CourseService;
import org.online.edu.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("index-front")
public class IndexFrontController {

    private CourseService courseService;

    private TeacherService teacherService;

    @GetMapping("index")
    public R index() {
        List<Course> courses = new LambdaQueryChainWrapper<>(courseService.getBaseMapper()).orderByDesc(Course::getGmtCreate).last("limit 8").list();
        List<Teacher> teachers = new LambdaQueryChainWrapper<>(teacherService.getBaseMapper()).orderByDesc(Teacher::getGmtCreate).last("limit 4").list();
        List<CourseDto> courseDtos = courses.stream().map(course -> BeanUtil.toBean(course, CourseDto.class)).collect(Collectors.toList());
        List<TeacherDto> teacherDtos = teachers.stream().map(teacher -> BeanUtil.toBean(teacher, TeacherDto.class)).collect(Collectors.toList());
        return R.ok(new HashMap<String, Object>(2) {{
            put("courses", courseDtos);
            put("teachers", teacherDtos);
        }});
    }
}
