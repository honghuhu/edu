package org.online.edu.filter;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Component
public class LoggingFilter extends CommonsRequestLoggingFilter {

    private static final List<String> EXCLUDE_LIST = new ArrayList<>();

    public LoggingFilter() {
        setIncludeQueryString(true);
        setIncludePayload(true);
        setMaxPayloadLength(1024);
    }

    static {
        EXCLUDE_LIST.add("/readiness/health");
        EXCLUDE_LIST.add("/v2/api-docs");
        EXCLUDE_LIST.add("/webjars/springfox-swagger-ui");
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        if (!super.shouldLog(request)) {
            return false;
        }

        boolean exclude = false;
        String uri = request.getRequestURI();
        for (String ex : EXCLUDE_LIST) {
            if (uri.contains(ex)) {
                exclude = true;
                break;
            }
        }

        return !exclude;
    }
}