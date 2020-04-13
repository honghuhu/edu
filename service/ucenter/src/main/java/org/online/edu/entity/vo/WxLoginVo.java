package org.online.edu.entity.vo;

import lombok.Data;

@Data
public class WxLoginVo {

    private String access_token;

    private String openid;

    private String nickname;

    private String headimgurl;
}
