package org.online.common.exception.handler;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        log.error("<全局异常> ", e.getMessage());
        return R.failed(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R error(MethodArgumentNotValidException e) {
        log.error("<参数绑定异常> " + e.getMessage());
        return R.failed(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(EduException.class)
    public R error(EduException e) {
        return R.failed(e.getMsg()).setCode(e.getCode());
    }
}
