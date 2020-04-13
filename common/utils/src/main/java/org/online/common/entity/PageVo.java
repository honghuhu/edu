package org.online.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageVo {

    @ApiModelProperty(value = "当前页")
    private int current = 1;

    @ApiModelProperty(value = "每页展示记录数")
    private int limit = 10;
}
