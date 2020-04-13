package org.online.edu.controller;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public R login(@RequestBody Map map) {
        log.info("user: {}, password: {}", map.get("username"), map.get("password"));
        return R.ok(new HashMap<String, String>(1) {{
            put("token", "036900");
        }});
    }

    @GetMapping("info")
    public R info() {
        return R.ok(new HashMap<String, Object>(3) {{
            put("roles", new String[]{"admin"});
            put("name", "007");
            put("avatar", "https://imgkr.cn-bj.ufileos.com/39c870b9-8f10-4a32-afa8-9f28aa66feb5.png");
        }});
    }
}
