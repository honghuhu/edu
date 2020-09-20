package org.online.edu.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Chapter;
import org.online.edu.entity.vo.ChapterSaveOrUpdateVo;
import org.online.edu.entity.vo.ChapterVo;
import org.online.edu.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("chapter")
public class ChapterController {

    private ChapterService chapterService;

    @GetMapping("{courseId}")
    public List<ChapterVo> chapterVideo(@PathVariable String courseId) {
        return chapterService.chapterVideo(courseId);
    }

    @PostMapping("saveOrUpdate")
    public Boolean save(@RequestBody ChapterSaveOrUpdateVo chapterSaveOrUpdateVo) {
        return chapterService.saveOrUpdate(BeanUtil.toBean(chapterSaveOrUpdateVo, Chapter.class));
    }

    @DeleteMapping("{id}")
    public Boolean remove(@PathVariable String id) {
        return chapterService.removeById(id);
    }
}

