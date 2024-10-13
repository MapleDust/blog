package xyz.fcidd.blog.api.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.fcidd.blog.api.dto.R;
import xyz.fcidd.blog.api.dto.State;
import xyz.fcidd.blog.api.exception.Base64NullTextException;
import xyz.fcidd.blog.api.exception.ErrorBase64Exception;
import xyz.fcidd.blog.api.exception.NullBase64Exception;
import xyz.fcidd.blog.api.exception.ServiceException;

/**
 * 异常统一处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public <T> R<T> handlerException(Throwable e) {
        return switch (e) {
            //加密的文本为空
            case Base64NullTextException ignored -> R.failure(State.ERR_BASE64_NULL_TEXT.getCode(), e);
            //Base64编码为空
            case NullBase64Exception ignored -> R.failure(State.ERR_NULL_BASE64.getCode(), e);
            //无效的Base64编码
            case ErrorBase64Exception ignored -> R.failure(State.ERR_BASE64.getCode(), e);
            //未知错误
            default -> R.failure(State.ERR_UNKNOWN.getCode(), e);
        };
    }
}
