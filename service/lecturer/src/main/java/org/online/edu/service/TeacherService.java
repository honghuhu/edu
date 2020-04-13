package org.online.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.online.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.online.edu.entity.vo.TeacherListVo;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 007
 * @since 2020-03-28
 */
public interface TeacherService extends IService<Teacher> {

    IPage pageByParam(TeacherListVo teacherListVo);
}
