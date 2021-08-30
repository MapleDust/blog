package xyz.fcidd.blog.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.fcidd.blog.dto.R;
import xyz.fcidd.blog.exception.Base64NullTextException;
import xyz.fcidd.blog.exception.ErrorBase64Exception;
import xyz.fcidd.blog.exception.NullBase64Exception;
import xyz.fcidd.blog.exception.ServiceException;

/**
 * 异常统一处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public R handlerException(Throwable e) {
        //加密的文本为空
        if (e instanceof Base64NullTextException) {
            return R.failure(R.State.ERR_BASE64_NULL_TEXT, e);
            //Base64编码为空
        } else if (e instanceof NullBase64Exception) {
            return R.failure(R.State.ERR_NULL_BASE64, e);
            //无效的Base64编码
        } else if (e instanceof ErrorBase64Exception) {
            return R.failure(R.State.ERR_BASE64, e);
        } else {
            //未知错误
            return R.failure(R.State.ERR_UNKNOWN, e);
        }
    }
}
