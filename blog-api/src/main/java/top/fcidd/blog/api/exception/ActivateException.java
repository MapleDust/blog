package top.fcidd.blog.api.exception;

public class ActivateException extends BlogApiException {
    public ActivateException() {
    }

    public ActivateException(String message) {
        super(message);
    }

    public ActivateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActivateException(Throwable cause) {
        super(cause);
    }

    public ActivateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
