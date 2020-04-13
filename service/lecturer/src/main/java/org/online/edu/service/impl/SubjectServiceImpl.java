package org.online.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Subject;
import org.online.edu.entity.dto.TreeSubjectDto;
import org.online.edu.entity.excel.SubjectDataVo;
import org.online.edu.listener.SubjectExcelListener;
import org.online.edu.mapper.SubjectMapper;
import org.online.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-03-31
 */
@Service
@AllArgsConstructor
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void importSubject(MultipartFile multipartFile) {
        try {
            EasyExcel.read(multipartFile.getInputStream(), SubjectDataVo.class, new SubjectExcelListener(this)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TreeSubjectDto> tree() {
        List<Subject> tier1Subject = list(new LambdaQueryWrapper<Subject>().eq(Subject::getParentId, 0));
        return tier1Subject.stream().map(one -> {
            TreeSubjectDto treeSubjectDto = BeanUtil.toBean(one, TreeSubjectDto.class);
            List<Subject> ter2Subject = list(new LambdaQueryWrapper<Subject>().eq(Subject::getParentId, one.getId()));
            treeSubjectDto.setChildren(ter2Subject.stream().map(two -> BeanUtil.toBean(two, TreeSubjectDto.class)).collect(Collectors.toList()));
            return treeSubjectDto;
        }).collect(Collectors.toList());
    }
}
