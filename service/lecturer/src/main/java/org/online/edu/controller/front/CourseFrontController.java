package org.online.edu.controller.front;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.online.edu.entity.dto.CourseDto;
import org.online.edu.entity.vo.CourseListVo;
import org.online.edu.service.CourseService;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = {"课程前台接口"})
@RequestMapping("course-front")
public class CourseFrontController {

    private CourseService courseService;

    @ApiOperation(value = "课程列表")
    @GetMapping("{page}/{limit}")
    public R<IPage<CourseDto>> page(@PathVariable Integer page, @PathVariable Integer limit) {
        CourseListVo courseListVo = new CourseListVo();
        courseListVo.setCurrent(page);
        courseListVo.setLimit(limit);
        IPage pageByParam = courseService.pageByParam(courseListVo);
        pageByParam.setRecords((List) pageByParam.getRecords().stream().map(course -> BeanUtil.toBean(course, CourseDto.class)).collect(Collectors.toList()));
        return R.ok(pageByParam);
    }
}