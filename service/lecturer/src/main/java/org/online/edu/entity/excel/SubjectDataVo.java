package org.online.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectDataVo {

    @ExcelProperty(value = "一级分类")
    private String tier1Name;

    @ExcelProperty(value = "二级分类")
    private String tier2Name;

}
