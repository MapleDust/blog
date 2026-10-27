package top.fcidd.blog.api.exception;

public class ChangeUserException extends BlogApiException {
    public ChangeUserException() {
    }

    public ChangeUserException(String message) {
        super(message);
    }

    public ChangeUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeUserException(Throwable cause) {
        super(cause);
    }

    public ChangeUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
