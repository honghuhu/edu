package org.online.edu.entity.dto.front;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.online.edu.entity.dto.CourseDto;

@Data
public class CourseFrontDto extends CourseDto {

    @ApiModelProperty(value = "一级分类")
    private String tier1Subject;

    @ApiModelProperty(value = "二级分类")
    private String tier2Subject;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "讲师名称")
    private String teacherName;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    @ApiModelProperty(value = "讲师描述")
    private String intro;
}
