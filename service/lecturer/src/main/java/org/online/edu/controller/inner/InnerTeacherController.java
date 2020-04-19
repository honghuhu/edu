package org.online.edu.controller.inner;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.online.edu.entity.dto.TeacherDto;
import org.online.edu.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 讲师 前端控制器 内部调用接口
 * </p>
 *
 * @author 007
 * @since 2020-03-28
 */
@RestController
@AllArgsConstructor
@Api(tags = {"讲师用于内部调用"})
@RequestMapping("inner/teacher")
public class InnerTeacherController {

    private TeacherService teacherService;

    @GetMapping("{id}")
    public TeacherDto info(@PathVariable String id) {
        return BeanUtil.toBean(teacherService.getById(id), TeacherDto.class);
    }
}