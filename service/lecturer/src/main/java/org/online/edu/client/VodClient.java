package org.online.edu.client;

import com.baomidou.mybatisplus.extension.api.R;
import org.online.edu.client.fallback.VodFallbackClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "vod", fallback = VodFallbackClient.class)
public interface VodClient {
    @DeleteMapping(value = "vod/video/{videoId}")
    R removeVideo(@PathVariable("videoId") String videoId);
}
