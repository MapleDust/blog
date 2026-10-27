package top.fcidd.blog.api.controller;

import jakarta.validation.ValidationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fcidd.blog.api.dto.Result;
import top.fcidd.blog.api.exception.BlogApiException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> validException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(400, message);
    }

    @ExceptionHandler(ValidationException.class)
    public Result<Void> validationException(ValidationException e) {
        return Result.fail(400, e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Result<Void> exception(Exception e) {
        return Result.fail(400, e.getMessage());
    }

    @ExceptionHandler(BlogApiException.class)
    public Result<Void> exception(BlogApiException e) {
        return Result.fail(400, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> globalException(Exception e) {
        e.printStackTrace();
        return Result.fail("服务器内部异常");
    }
}
