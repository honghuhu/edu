package org.online.edu.config;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.online.edu.exception.EduException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author yuxiang.wang
 */
@Slf4j
@RestControllerAdvice(basePackages = "org.online.edu.controller")
public class ResponseBodyAdvisor implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return aClass == MappingJackson2HttpMessageConverter.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof R) {
            return body;
        }
        return R.ok(body);
    }

    @ExceptionHandler(Exception.class)
    public R<String> error(Exception e) {
        e.printStackTrace();
        log.error("<全局异常> {}", e.getMessage());
        return R.failed(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> error(MethodArgumentNotValidException e) {
        log.error("<参数绑定异常> {}", e.getMessage());
        return R.failed(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(EduException.class)
    public R<Object> error(EduException e) {
        return R.failed(e.getMsg()).setCode(e.getCode());
    }
}