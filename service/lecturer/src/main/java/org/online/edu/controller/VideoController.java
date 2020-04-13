package org.online.edu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.online.edu.client.VodClient;
import org.online.edu.entity.Video;
import org.online.edu.entity.vo.VideoSaveOrUpdateVo;
import org.online.edu.service.VideoService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-04-01
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("video")
public class VideoController {

    private VodClient vodClient;
    private VideoService videoService;

    @PostMapping("saveOrUpdate")
    public R<Boolean> save(@RequestBody VideoSaveOrUpdateVo videoSaveOrUpdateVo) {
        return R.ok(videoService.saveOrUpdate(BeanUtil.toBean(videoSaveOrUpdateVo, Video.class)));
    }

    @DeleteMapping("{id}")
    public R<Boolean> remove(@PathVariable String id) {
        Video video = videoService.getById(id);
        if (ObjectUtils.isNotEmpty(video)) {
            R r = vodClient.removeVideo(video.getVideoSourceId());
            return r.getCode() == ApiErrorCode.FAILED.getCode() ? r : R.ok(videoService.removeById(id));
        }
        return R.failed("删除失败");
    }

    @GetMapping("{id}")
    public R<VideoSaveOrUpdateVo> info(@PathVariable String id) {
        return R.ok(BeanUtil.toBean(videoService.getById(id), VideoSaveOrUpdateVo.class));
    }
}

