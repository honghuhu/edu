package org.online.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.online.edu.entity.Member;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author 007
 * @since 2020-04-11
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegisterDay(String day);
}
