package org.online.edu.client;

import org.online.edu.client.fallback.UCenterFallbackClient;
import org.online.edu.entity.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ucenter", fallback = UCenterFallbackClient.class)
public interface UCenterClient {

    @GetMapping("ucenter/inner/member/{id}")
    MemberDto info(@PathVariable String id);
}
