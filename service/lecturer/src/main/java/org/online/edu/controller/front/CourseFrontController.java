package org.online.edu.controller.front;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.online.edu.client.OrderClient;
import org.online.edu.entity.dto.CourseDto;
import org.online.edu.entity.dto.front.CourseFrontDto;
import org.online.edu.entity.vo.ChapterVo;
import org.online.edu.entity.vo.CourseListVo;
import org.online.edu.entity.vo.front.CourseFrontVo;
import org.online.edu.service.ChapterService;
import org.online.edu.service.CourseService;
import org.online.edu.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
@Api(tags = {"课程前台接口"})
@RequestMapping("course-front")
public class CourseFrontController {

    private CourseService courseService;
    private ChapterService chapterService;
    private OrderClient orderClient;

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

    @ApiOperation(value = "课程列表")
    @PostMapping("page")
    public R<IPage<CourseDto>> page(@RequestBody CourseFrontVo courseFrontVo) {
        IPage pageByParam = courseService.pageByParam(courseFrontVo);
        pageByParam.setRecords((List) pageByParam.getRecords().stream().map(course -> BeanUtil.toBean(course, CourseDto.class)).collect(Collectors.toList()));
        return R.ok(pageByParam);
    }

    @ApiOperation(value = "课程详情")
    @GetMapping("{id}")
    public R detail(@PathVariable @ApiParam(name = "courseId", value = "课程ID", required = true) String id, HttpServletRequest request) {
        // 课程详情
        CourseFrontDto courseFrontDto = courseService.findCourseFrontDtoById(id);
        // 小结集合
        List<ChapterVo> chapters = chapterService.chapterVideo(id);
        // 是否购买
        boolean isBuy = orderClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(request), id);
        return R.ok(new HashMap<String, Object>(3) {{
            put("course", courseFrontDto);
            put("chapters", chapters);
            put("isBuy", isBuy);
        }});
    }
}