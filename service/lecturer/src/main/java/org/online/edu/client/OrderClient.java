package org.online.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("order")
public interface OrderClient {

    @GetMapping("/order/inner/order/isBuyCourse/{memberId}/{courseId}")
    boolean isBuyCourse(@PathVariable String memberId, @PathVariable String courseId);
}