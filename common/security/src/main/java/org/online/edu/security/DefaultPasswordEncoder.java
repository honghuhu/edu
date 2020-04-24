package org.online.edu.security;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 * t密码的处理方法类型
 * </p>
 *
 * @author 镜子白
 * @since 2019-11-08
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    /**
     * @param strength the log rounds to use, between 4 and 31
     */
    public DefaultPasswordEncoder(int strength) {

    }

    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtil.md5Hex((rawPassword.toString()));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(DigestUtil.md5Hex((rawPassword.toString())));
    }
}