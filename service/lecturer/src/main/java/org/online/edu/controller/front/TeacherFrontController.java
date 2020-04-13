package org.online.edu.controller.front;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.online.edu.entity.dto.TeacherDto;
import org.online.edu.entity.vo.TeacherListVo;
import org.online.edu.service.TeacherService;
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
@Api(tags = {"讲师前台接口"})
@RequestMapping("teacher-front")
public class TeacherFrontController {

    private TeacherService teacherService;

    @ApiOperation(value = "讲师列表")
    @GetMapping("{page}/{limit}")
    public R<IPage<TeacherDto>> page(@PathVariable Integer page, @PathVariable Integer limit) {
        TeacherListVo teacherListVo = new TeacherListVo();
        teacherListVo.setCurrent(page);
        teacherListVo.setLimit(limit);
        IPage pageByParam = teacherService.pageByParam(teacherListVo);
        pageByParam.setRecords((List) pageByParam.getRecords().stream().map(teacher -> BeanUtil.toBean(teacher, TeacherDto.class)).collect(Collectors.toList()));
        return R.ok(pageByParam);
    }
}