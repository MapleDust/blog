package top.fcidd.blog.api.exception;

public class SendMailException extends BlogApiException {
    public SendMailException(String message) {
        super(message);
    }

    public SendMailException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendMailException(Throwable cause) {
        super(cause);
    }

    public SendMailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SendMailException() {
    }
}
