package xyz.fcidd.blog.exception;

/**
 * 无效的Base64编码
 */
public class ErrorBase64Exception extends ServiceException {
    public ErrorBase64Exception() {
    }

    public ErrorBase64Exception(String message) {
        super(message);
    }

    public ErrorBase64Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorBase64Exception(Throwable cause) {
        super(cause);
    }

    public ErrorBase64Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
