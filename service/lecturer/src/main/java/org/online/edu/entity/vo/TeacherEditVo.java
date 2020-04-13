package org.online.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("Teacher 修改对象")
public class TeacherEditVo extends TeacherVo {

    @ApiModelProperty(value = "注解id", required = true)
    @NotNull(message = "id 不能为空")
    private String id;
}
