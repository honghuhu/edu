package org.online.edu.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.online.edu.entity.vo.CourseInfoVo;

@Data
public class CourseInfoDto extends CourseInfoVo {

    @ApiModelProperty(value = "课程id")
    private String id;
}
