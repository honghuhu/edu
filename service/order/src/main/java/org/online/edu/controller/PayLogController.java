package org.online.edu.controller;

import lombok.AllArgsConstructor;
import org.online.edu.service.PayLogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-04-16
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("pay-log")
public class PayLogController {

    private PayLogService payLogService;
}

