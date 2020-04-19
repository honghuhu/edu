package org.online.edu.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.online.edu.entity.BaseDto;

@Data
public class BannerDto extends BaseDto {
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
