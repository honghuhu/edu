package org.online.edu.controller.inner;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.online.edu.entity.dto.CourseInfoDto;
import org.online.edu.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器 内部接口
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
@RestController
@Api(tags = {"课程用于内部调用"})
@AllArgsConstructor
@RequestMapping("inner/course")
public class InnerCourseController {

    private CourseService courseService;

    @GetMapping("{id}")
    public CourseInfoDto findById(@PathVariable String id) {
        return courseService.findById(id);
    }

}

