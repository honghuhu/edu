package org.online.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.online.edu.entity.Order;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author 007
 * @since 2020-04-16
 */
public interface OrderService extends IService<Order> {

    String save(String courseId, String memberId);
}
