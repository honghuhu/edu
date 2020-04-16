package org.online.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Video对象", description = "小结")
public class VideoVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "小结名称")
    private String title;

    @ApiModelProperty(value = "是否免费: 0收费 1免费")
    private Integer isFree;

    @ApiModelProperty(value = "云端视频资源")
    private String videoSourceId;
}
