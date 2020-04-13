package org.online.edu.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.online.common.exception.handler.EduException;
import org.online.edu.service.VideoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.online.edu.utils.AliyunVodSDKUtils.initVodClient;

@Api
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/video")
public class VideoController {

    private VideoService videoService;

    @PostMapping("upload")
    public R<String> uploadVideo(@ApiParam(name = "file", value = "文件", required = true) @RequestParam("file") MultipartFile file) {
        return R.ok(videoService.uploadVideo(file));
    }

    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable String videoId) {
        DefaultAcsClient defaultAcsClient = initVodClient();
        DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
        deleteVideoRequest.setVideoIds(videoId);
        try {
            defaultAcsClient.getAcsResponse(deleteVideoRequest);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new EduException(20001, "删除视频失败");
        }
        return R.ok("删除成功");
    }
}