package org.online.edu.client;

import org.online.edu.client.fallback.UCenterFallbackClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ucenter", fallback = UCenterFallbackClient.class)
public interface UCenterClient {

    @GetMapping("ucenter/inner/member/countRegister/{day}")
    int countRegister(@PathVariable String day);
}
