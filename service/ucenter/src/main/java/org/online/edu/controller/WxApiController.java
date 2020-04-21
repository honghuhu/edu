package org.online.edu.controller;

import cn.hutool.core.net.URLEncoder;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.online.edu.entity.Member;
import org.online.edu.entity.vo.WxLoginVo;
import org.online.edu.service.MemberService;
import org.online.edu.utils.ConstantProperties;
import org.online.edu.utils.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("wx")
public class WxApiController {

    private RestTemplate restTemplate;
    private MemberService memberService;

    @GetMapping("qr")
    public String getCode() {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        //为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名
        String state = "imhelen";
        System.out.println("state = " + state);
        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟

        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantProperties.WX_OPEN_APP_ID,
                URLEncoder.DEFAULT.encode(ConstantProperties.WX_OPEN_REDIRECT_URL, Charset.defaultCharset()),
                state);
        return String.format("redirect:%s", qrcodeUrl);
    }

    @GetMapping("callback")
    public String callback(String code, String state) {
        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantProperties.WX_OPEN_APP_ID,
                ConstantProperties.WX_OPEN_APP_SECRET,
                code);
        String wxLoginString = restTemplate.getForObject(accessTokenUrl, String.class);
        WxLoginVo wxLoginVo = JSON.parseObject(wxLoginString, WxLoginVo.class);
        log.info(JSON.toJSONString(wxLoginVo));

        //查询数据库当前用用户是否曾经使用过微信登录
        Member member = new LambdaQueryChainWrapper<>(memberService.getBaseMapper()).eq(Member::getOpenid, wxLoginVo.getOpenid()).one();
        if (ObjectUtils.isEmpty(member)) {
            log.info("新用户注册");
            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, wxLoginVo.getAccess_token(), wxLoginVo.getOpenid());
            wxLoginString = restTemplate.getForObject(userInfoUrl, String.class);
            WxLoginVo userInfo = JSON.parseObject(wxLoginString, WxLoginVo.class);
            log.info(JSON.toJSONString(userInfo));
            //向数据库中插入一条记录
            member = new Member().setOpenid(wxLoginVo.getOpenid()).setNickname(userInfo.getNickname()).setAvatar(userInfo.getHeadimgurl());
            memberService.save(member);
        }
        String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return String.format("redirect:http://localhost:3000?token=%s", jwtToken);
    }
}
