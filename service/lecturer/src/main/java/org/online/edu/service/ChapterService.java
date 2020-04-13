package org.online.edu.service;

import org.online.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import org.online.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> chapterVideo(String courseId);
}
