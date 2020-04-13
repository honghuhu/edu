package org.online.edu.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.online.common.entity.BaseDto;

import java.math.BigDecimal;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
@Data
public class CourseDto extends BaseDto {

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    @ApiModelProperty(value = "课程专业父级ID")
    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;
}
