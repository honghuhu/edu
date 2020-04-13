package org.online.edu.client;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.stereotype.Component;

@Component
public class VodFallbackClient implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.failed("time out");
    }
}