package org.online.edu.controller;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.online.edu.entity.dto.TreeSubjectDto;
import org.online.edu.service.SubjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-03-31
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("subject")
public class SubjectController {

    private SubjectService subjectService;

    @ApiOperation(value = "添加讲师")
    @PostMapping("import")
    public R<Boolean> importSubject(MultipartFile multipartFile) {
        subjectService.importSubject(multipartFile);
        return R.ok(true);
    }

    @ApiOperation(value = "课程分类Tree")
    @GetMapping("tree")
    public R<List<TreeSubjectDto>> treeSubject() {
        return R.ok(subjectService.tree());
    }
}