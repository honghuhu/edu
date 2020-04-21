package org.online.edu.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Member;
import org.online.edu.entity.dto.MemberDto;
import org.online.edu.entity.vo.MemberVo;
import org.online.edu.entity.vo.RegisterVo;
import org.online.edu.service.MemberService;
import org.online.edu.utils.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 007
 * @since 2020-04-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("member")
public class MemberController {

    private MemberService memberService;

    @PostMapping("login")
    public R login(@RequestBody @Valid MemberVo memberVo) {
        return R.ok(memberService.login(memberVo));
    }

    @PostMapping("register")
    public R register(@RequestBody @Valid RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok(null).setMsg("注册成功");
    }

    @GetMapping("info")
    public R info(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(id);
        return R.ok(BeanUtil.toBean(member, MemberDto.class));
    }
}

