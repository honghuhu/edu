package org.online.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.online.edu.entity.Subject;
import org.online.edu.entity.dto.TreeSubjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 007
 * @since 2020-03-31
 */
public interface SubjectService extends IService<Subject> {

    void importSubject(MultipartFile multipartFile);

    List<TreeSubjectDto> tree();
}
