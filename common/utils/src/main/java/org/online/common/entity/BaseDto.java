package org.online.common.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class BaseDto {
    @ApiParam(value = "注解id")
    private String id;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
}
