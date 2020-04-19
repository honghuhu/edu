package org.online.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel("Teacher 添加对象")
public class TeacherVo {

    @ApiModelProperty(value = "讲师姓名", required = true)
    @NotNull(message = "讲师名称不能为空")
    @Size(min = 1,max = 20,message = "讲师名称长度有误")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    private String intro;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    @Min(value = 1, message = "头衔参数错误")
    @Max(value = 3, message = "头衔参数错误")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;
}
