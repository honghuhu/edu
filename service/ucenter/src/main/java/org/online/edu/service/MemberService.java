package org.online.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.online.edu.entity.Member;
import org.online.edu.entity.vo.MemberVo;
import org.online.edu.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 007
 * @since 2020-04-11
 */
public interface MemberService extends IService<Member> {

    String login(MemberVo memberVo);

    void register(RegisterVo registerVo);

    Integer countRegisterDay(String day);
}
