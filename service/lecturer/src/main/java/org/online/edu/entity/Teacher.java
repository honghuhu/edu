package org.online.edu.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.online.common.entity.BasePo;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author 007
 * @since 2020-03-28
 */
@Data
@Accessors(chain = true)
@TableName("edu_teacher")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Teacher对象", description = "讲师")
public class Teacher extends BasePo {

    private static final long serialVersionUID = -8624285618807690552L;

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

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;
}
