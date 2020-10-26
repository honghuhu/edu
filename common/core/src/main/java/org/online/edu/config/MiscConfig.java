package org.online.edu.config;

import org.online.edu.filter.LoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yuxiang.wang
 * @Date 2020/10/26 8:56 下午
 **/
@Configuration
public class MiscConfig {
    @Bean
    public LoggingFilter loggingFilter() {
        LoggingFilter filter = new LoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(1024);
        return filter;
    }
}
