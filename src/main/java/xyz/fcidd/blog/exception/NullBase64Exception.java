package xyz.fcidd.blog.exception;

/**
 * base64编码为空
 */
public class NullBase64Exception extends ServiceException{
    public NullBase64Exception() {
    }

    public NullBase64Exception(String message) {
        super(message);
    }

    public NullBase64Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public NullBase64Exception(Throwable cause) {
        super(cause);
    }

    public NullBase64Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
