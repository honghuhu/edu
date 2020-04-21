package org.online.edu.controller;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import org.online.edu.service.PayLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-04-16
 */
@RestController
@AllArgsConstructor
@RequestMapping("log")
public class PayLogController {

    private PayLogService payLogService;

    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.createNative(orderNo);
        return R.ok(map);
    }

    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {
            return R.failed("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return R.ok("支付成功");
        }
        return R.ok("支付中").setCode(25000).setMsg("支付中");
    }
}

