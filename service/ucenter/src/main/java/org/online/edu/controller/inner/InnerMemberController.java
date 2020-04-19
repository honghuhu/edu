package org.online.edu.controller.inner;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.online.edu.entity.Member;
import org.online.edu.entity.dto.MemberDto;
import org.online.edu.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器 内部调用
 * </p>
 *
 * @author 007
 * @since 2020-04-17
 */
@RestController
@AllArgsConstructor
@RequestMapping("inner/member")
public class InnerMemberController {

    private MemberService memberService;

    @GetMapping("{id}")
    public MemberDto info(@PathVariable String id) {
        Member member = memberService.getById(id);
        return BeanUtil.toBean(member, MemberDto.class);
    }

    @GetMapping("countRegister/{day}")
    public Integer countRegister(@PathVariable String day) {
        return memberService.countRegisterDay(day);
    }
}

