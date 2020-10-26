package org.online.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.online.edu.entity.Subject;
import org.online.edu.entity.excel.SubjectDataVo;
import org.online.edu.exception.EduException;
import org.online.edu.service.SubjectService;
import org.springframework.util.ObjectUtils;

public class SubjectExcelListener extends AnalysisEventListener<SubjectDataVo> {

    private SubjectService subjectService;

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectDataVo subjectDataVo, AnalysisContext analysisContext) {
        if (ObjectUtils.isEmpty(subjectDataVo)) {
            throw new EduException(200, "文件数据为空");
        }
        // 判断是否存在一级目录
        Subject tier1Subject = subjectService.getOne(new LambdaQueryWrapper<Subject>()
                .eq(Subject::getTitle, subjectDataVo.getTier1Name())
                .eq(Subject::getParentId, "0"));
        if (ObjectUtils.isEmpty(tier1Subject)) {
            tier1Subject = new Subject().setTitle(subjectDataVo.getTier1Name()).setParentId("0");
            subjectService.save(tier1Subject);
        }
        // 判断是否存在二级目录
        Subject tier2Subject = subjectService.getOne(new LambdaQueryWrapper<Subject>()
                .eq(Subject::getTitle, subjectDataVo.getTier2Name())
                .eq(Subject::getParentId, tier1Subject.getId()));
        if (ObjectUtils.isEmpty(tier2Subject)) {
            tier2Subject = new Subject().setTitle(subjectDataVo.getTier2Name()).setParentId(tier1Subject.getId());
            subjectService.save(tier2Subject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
