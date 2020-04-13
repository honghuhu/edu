package org.online.edu.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TreeSubjectDto {

    private String id;

    private String title;

    private List<TreeSubjectDto> children;
}
