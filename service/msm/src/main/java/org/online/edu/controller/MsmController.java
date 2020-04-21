package org.online.edu.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.online.edu.constant.RedisConstant;
import org.online.edu.service.MsmService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@RequestMapping("sms")
public class MsmController {

    private MsmService msmService;
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("send/{phone}")
    public R send(@PathVariable String phone) {
        String oldCode = redisTemplate.opsForValue().get(RedisConstant.Sms.Register(phone));
        if (!StringUtils.isEmpty(oldCode)) {
            return R.ok(null).setMsg("发送成功");
        }
        String code = RandomUtil.randomNumbers(5);
        boolean flag = msmService.send(phone, new HashMap<String, String>(1) {{
            put("code", code);
        }});
        if (flag) {
            redisTemplate.opsForValue().set(RedisConstant.Sms.Register(phone), code, 1, TimeUnit.MINUTES);
            return R.ok(null).setMsg("发送成功");
        }
        return R.failed("发送短信失败");
    }
}
