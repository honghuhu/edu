package org.online.edu.service.impl;

import lombok.AllArgsConstructor;
import org.online.edu.entity.Order;
import org.online.edu.mapper.OrderMapper;
import org.online.edu.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-16
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
