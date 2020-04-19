package org.online.edu.controller.inner;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Order;
import org.online.edu.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单 前端控制器 内部调用
 * </p>
 *
 * @author 007
 * @since 2020-04-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("inner/order")
public class InnerOrderController {

    private OrderService orderService;

    @GetMapping("isBuyCourse/{memberId}/{courseId}")
    public boolean info(@PathVariable @ApiParam(value = "用户id") String memberId, @PathVariable @ApiParam(value = "课程id") String courseId) {
        int count = new LambdaQueryChainWrapper<>(orderService.getBaseMapper())
                .eq(Order::getMemberId, memberId)
                .eq(Order::getCourseId, courseId)
                .eq(Order::getStatus, 1)
                .count();
        return count > 0;
    }
}

