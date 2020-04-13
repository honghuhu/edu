package org.online.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.online.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import org.online.edu.entity.dto.CourseInfoDto;
import org.online.edu.entity.vo.CourseInfoVo;
import org.online.edu.entity.vo.CourseListVo;
import org.online.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    void modifyCourseInfo(String id, CourseInfoVo courseInfoVo);

    CourseInfoDto findById(String id);

    CoursePublishVo findCoursePublishVoById(String id);

    IPage pageByParam(CourseListVo courseListVo);
}
