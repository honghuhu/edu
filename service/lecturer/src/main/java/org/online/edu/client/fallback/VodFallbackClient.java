package org.online.edu.client.fallback;

import com.baomidou.mybatisplus.extension.api.R;
import org.online.edu.client.VodClient;
import org.springframework.stereotype.Component;

@Component
public class VodFallbackClient implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.failed("time out");
    }
}