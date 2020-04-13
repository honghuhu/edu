package org.online.edu.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 初始化业务中所有常量
 */
@Component
public class ConstantProperties implements InitializingBean {

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Value("${aliyun.vod.file.keyid}")
    private String keyId;
    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;

    @Override
    public void afterPropertiesSet() {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}