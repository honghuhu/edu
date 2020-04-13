package org.online.edu.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.online.edu.entity.vo.CourseInfoVo;

import java.math.BigDecimal;

@Data
public class CourseInfoDto extends CourseInfoVo {

    @ApiModelProperty(value = "课程id")
    private String id;
}
