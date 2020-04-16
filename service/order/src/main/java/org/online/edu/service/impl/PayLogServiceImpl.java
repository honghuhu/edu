package org.online.edu.service.impl;

import lombok.AllArgsConstructor;
import org.online.edu.entity.PayLog;
import org.online.edu.mapper.PayLogMapper;
import org.online.edu.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-16
 */
@Service
@AllArgsConstructor
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
