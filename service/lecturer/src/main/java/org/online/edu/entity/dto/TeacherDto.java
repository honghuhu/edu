package org.online.edu.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.online.common.entity.BaseDto;

@Data
public class TeacherDto extends BaseDto {
    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    private String intro;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;
}
