package org.online.edu.service.impl;

import org.online.edu.entity.CourseDescription;
import org.online.edu.mapper.CourseDescriptionMapper;
import org.online.edu.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

}
