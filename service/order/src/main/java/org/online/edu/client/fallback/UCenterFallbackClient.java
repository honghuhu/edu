package org.online.edu.client.fallback;

import org.online.edu.client.UCenterClient;
import org.online.edu.entity.dto.MemberDto;
import org.online.edu.exception.EduException;
import org.springframework.stereotype.Component;

@Component
public class UCenterFallbackClient implements UCenterClient {
    @Override
    public MemberDto info(String id) {
        throw new EduException(20001, "调用户中心报错");
    }
}
