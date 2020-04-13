package org.online.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.online.common.entity.PageVo;

@Data
@ApiModel("Course 查询对象")
public class CourseListVo extends PageVo {

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;
}
