package xyz.fcidd.blog.exception;

/**
 * Base64加密文本为空
 */
public class Base64NullTextException extends ServiceException {
    public Base64NullTextException() {
    }

    public Base64NullTextException(String message) {
        super(message);
    }

    public Base64NullTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public Base64NullTextException(Throwable cause) {
        super(cause);
    }

    public Base64NullTextException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
