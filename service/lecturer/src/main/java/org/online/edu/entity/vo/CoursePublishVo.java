package org.online.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "课程发布信息")
public class CoursePublishVo{

  private String title;

  private String cover;

  private Integer lessonNum;

  private String tier1Subject;

  private String tier2Subject;

  private String teacherName;

  private String price;//只用于显示

}