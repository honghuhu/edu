package org.online.edu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.AllArgsConstructor;
import org.online.edu.constant.RedisConstant;
import org.online.edu.entity.Member;
import org.online.edu.entity.vo.MemberVo;
import org.online.edu.entity.vo.RegisterVo;
import org.online.edu.exception.handler.EduException;
import org.online.edu.mapper.MemberMapper;
import org.online.edu.service.MemberService;
import org.online.edu.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 007
 * @since 2020-04-11
 */
@Service
@AllArgsConstructor
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(MemberVo memberVo) {
        Member member = new LambdaQueryChainWrapper<>(baseMapper).eq(Member::getMobile, memberVo.getUsername()).one();
        if (ObjectUtils.isEmpty(member)) {
            throw new EduException(20001, "未找到当前用户");
        }
        if (!member.getPassword().equals(DigestUtil.md5Hex(memberVo.getPassword()))) {
            throw new EduException(20001, "用户名或密码错误, 请重新输入!");
        }
        if (member.getIsDeleted()) {
            throw new EduException(20001, "当前用户已禁用, 请联系管理员!");
        }
        return JwtUtils.getJwtToken(member.getId(), member.getNickname());
    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = redisTemplate.opsForValue().get(RedisConstant.Sms.Register(registerVo.getMobile()));
        if (!registerVo.getCode().equals(code)) {
            throw new EduException(20001, "注册失败");
        }

        boolean flag = SqlHelper.retBool(new LambdaQueryChainWrapper<>(baseMapper).eq(Member::getMobile, registerVo.getMobile()).count());
        if (flag) {
            throw new EduException(20001, "注册失败, 已存在当前用户");
        }

        Member entity = BeanUtil.toBean(registerVo, Member.class);
        entity.setPassword(DigestUtil.md5Hex(entity.getPassword()));
        entity.setIsDisabled(false);
        entity.setAvatar("https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        save(entity);
    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
