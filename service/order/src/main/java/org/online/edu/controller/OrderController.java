package org.online.edu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Order;
import org.online.edu.entity.dto.OrderDto;
import org.online.edu.service.OrderService;
import org.online.edu.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-04-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("order")
public class OrderController {

    private OrderService orderService;

    @PostMapping("{courseId}")
    public R save(@PathVariable @ApiParam("课程id") String courseId, HttpServletRequest request) {
        return R.ok(orderService.save(courseId, JwtUtils.getMemberIdByJwtToken(request)));
    }

    @GetMapping("{orderNo}")
    public R<OrderDto> info(@PathVariable @ApiParam(value = "订单编号") String orderNo) {
        Order order = new LambdaQueryChainWrapper<>(orderService.getBaseMapper()).eq(Order::getOrderNo, orderNo).one();
        return R.ok(BeanUtil.toBean(order, OrderDto.class));
    }
}

