package org.online.edu.mapper;

import org.online.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.online.edu.entity.dto.front.CourseFrontDto;
import org.online.edu.entity.vo.CoursePublishVo;
import org.online.edu.entity.vo.front.CourseFrontVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo selectCoursePublishVoById(String id);

    CourseFrontDto selectCourseFrontDtoById(String id);
}
