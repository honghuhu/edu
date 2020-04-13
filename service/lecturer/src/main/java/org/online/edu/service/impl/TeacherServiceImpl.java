package org.online.edu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.online.edu.entity.Teacher;
import org.online.edu.entity.vo.TeacherListVo;
import org.online.edu.mapper.TeacherMapper;
import org.online.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-03-28
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public IPage<Teacher> pageByParam(TeacherListVo teacherListVo) {
        return new LambdaQueryChainWrapper<>(baseMapper)
                .like(StringUtils.isNotEmpty(teacherListVo.getName()), Teacher::getName, teacherListVo.getName())
                .eq(null != teacherListVo.getLevel(), Teacher::getLevel, teacherListVo.getLevel())
                .ge(StringUtils.isNotEmpty(teacherListVo.getBegin()), Teacher::getGmtCreate, teacherListVo.getBegin())
                .le(StringUtils.isNotEmpty(teacherListVo.getEnd()), Teacher::getGmtCreate, teacherListVo.getEnd())
                .orderByDesc(Teacher::getGmtCreate)
                .page(new Page<>(teacherListVo.getCurrent(), teacherListVo.getLimit()));
    }
}
