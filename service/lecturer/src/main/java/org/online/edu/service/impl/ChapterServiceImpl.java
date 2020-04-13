package org.online.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Chapter;
import org.online.edu.entity.Video;
import org.online.edu.entity.vo.ChapterVo;
import org.online.edu.mapper.ChapterMapper;
import org.online.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.online.edu.service.VideoService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
@Service
@AllArgsConstructor
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    private VideoService videoService;

    @Override
    public List<ChapterVo> chapterVideo(String courseId) {
        // 获取所有章节
        List<Chapter> chapters = list(new LambdaQueryWrapper<Chapter>().eq(Chapter::getCourseId, courseId));
        return chapters.stream().map(chapter -> {
            ChapterVo chapterVo = BeanUtil.toBean(chapter, ChapterVo.class);
            // 获取所有小结
            List<Video> videos = videoService.list(new LambdaQueryWrapper<Video>().eq(Video::getCourseId, courseId).eq(Video::getChapterId, chapter.getId()));
            chapterVo.setChildren(videos.stream().map(video -> BeanUtil.toBean(video, ChapterVo.class)).collect(Collectors.toList()));
            return chapterVo;
        }).collect(Collectors.toList());
    }

    @Override
    public boolean removeById(Serializable id) {
        videoService.remove(new LambdaQueryWrapper<Video>().eq(Video::getChapterId, id));
        return SqlHelper.retBool(this.getBaseMapper().deleteById(id));
    }
}
