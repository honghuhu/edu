package org.online.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.online.edu.entity.Course;
import org.online.edu.entity.CourseDescription;
import org.online.edu.entity.dto.CourseInfoDto;
import org.online.edu.entity.dto.front.CourseFrontDto;
import org.online.edu.entity.vo.CourseInfoVo;
import org.online.edu.entity.vo.CourseListVo;
import org.online.edu.entity.vo.CoursePublishVo;
import org.online.edu.entity.vo.front.CourseFrontVo;
import org.online.edu.exception.handler.EduException;
import org.online.edu.mapper.CourseMapper;
import org.online.edu.service.CourseDescriptionService;
import org.online.edu.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
@Service
@AllArgsConstructor
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    private CourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        Course course = BeanUtil.toBean(courseInfoVo, Course.class);
        if (!this.save(course)) {
            throw new EduException(201, "保存失败!");
        }
        courseDescriptionService.save(new CourseDescription().setId(course.getId()).setDescription(courseInfoVo.getDescription()));
        return course.getId();
    }

    @Override
    public void modifyCourseInfo(String id, CourseInfoVo courseInfoVo) {
        Course course = BeanUtil.toBean(courseInfoVo, Course.class);
        course.setId(id);
        updateById(course);
        CourseDescription courseDescription = courseDescriptionService.getById(id);
        if (!courseInfoVo.getDescription().equals(courseDescription.getDescription())) {
            courseDescription.setDescription(courseInfoVo.getDescription());
            courseDescriptionService.updateById(courseDescription);
        }
    }

    @Override
    public CourseInfoDto findById(String id) {
        Course course = getById(id);
        if (ObjectUtil.isNotEmpty(course)) {
            CourseDescription courseDescription = courseDescriptionService.getById(id);
            CourseInfoDto courseInfoDto = BeanUtil.toBean(course, CourseInfoDto.class);
            courseInfoDto.setDescription(courseDescription.getDescription());
            return courseInfoDto;
        }
        return null;
    }

    @Override
    public CoursePublishVo findCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public IPage<Course> pageByParam(CourseListVo courseListVo) {
        return new LambdaQueryChainWrapper<>(baseMapper)
                .like(StringUtils.isNotEmpty(courseListVo.getTitle()), Course::getTitle, courseListVo.getTitle())
                .eq(StringUtils.isNotEmpty(courseListVo.getStatus()), Course::getStatus, courseListVo.getStatus())
                .orderByDesc(Course::getGmtModified)
                .page(new Page<>(courseListVo.getCurrent(), courseListVo.getLimit()));
    }

    @Override
    public IPage<Course> pageByParam(CourseFrontVo courseFrontVo) {
        return new LambdaQueryChainWrapper<>(baseMapper)
                .like(StringUtils.isNotEmpty(courseFrontVo.getTitle()), Course::getTitle, courseFrontVo.getTitle())
                .eq(StringUtils.isNotEmpty(courseFrontVo.getTeacherId()), Course::getTeacherId, courseFrontVo.getTeacherId())
                .eq(StringUtils.isNotEmpty(courseFrontVo.getSubjectParentId()), Course::getSubjectParentId, courseFrontVo.getSubjectParentId())
                .eq(StringUtils.isNotEmpty(courseFrontVo.getSubjectId()), Course::getSubjectId, courseFrontVo.getSubjectId())
                .orderBy(!ObjectUtils.isEmpty(courseFrontVo.getBuyCountSort()), ObjectUtils.isEmpty(courseFrontVo.getBuyCountSort()) ? true : courseFrontVo.getBuyCountSort(), Course::getBuyCount)
                .orderBy(!ObjectUtils.isEmpty(courseFrontVo.getPriceSort()), ObjectUtils.isEmpty(courseFrontVo.getPriceSort()) ? true : courseFrontVo.getPriceSort(), Course::getPrice)
                .orderBy(!ObjectUtils.isEmpty(courseFrontVo.getGmtCreateSort()), ObjectUtils.isEmpty(courseFrontVo.getGmtCreateSort()) ? true : courseFrontVo.getGmtCreateSort(), Course::getGmtCreate)
                .page(new Page<>(courseFrontVo.getCurrent(), courseFrontVo.getLimit()));
    }

    @Override
    public CourseFrontDto findCourseFrontDtoById(String id) {
        new LambdaUpdateChainWrapper<>(baseMapper).setSql("view_count = view_count + 1").eq(Course::getId, id).update();
        return baseMapper.selectCourseFrontDtoById(id);
    }
}
