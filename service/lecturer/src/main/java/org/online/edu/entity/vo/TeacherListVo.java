package org.online.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.online.edu.entity.PageVo;

@Data
@ApiModel("Teacher 查询对象")
public class TeacherListVo extends PageVo {

    @ApiModelProperty(value = "教师名称")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2002-01-01 11:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2022-01-01 11:10:10")
    private String end;
}
