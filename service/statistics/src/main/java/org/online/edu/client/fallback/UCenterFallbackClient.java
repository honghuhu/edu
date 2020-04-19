package org.online.edu.client.fallback;

import lombok.extern.slf4j.Slf4j;
import org.online.edu.client.UCenterClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UCenterFallbackClient implements UCenterClient {

    @Override
    public int countRegister(String day) {
        log.error("<调用户中心报错> day: {}", day);
        return 0;
    }
}
