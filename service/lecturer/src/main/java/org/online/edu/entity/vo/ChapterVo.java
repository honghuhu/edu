package org.online.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "Chapter对象", description = "课程")
public class ChapterVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "小结集合")
    private List<ChapterVo> children;
}
