package top.fcidd.blog.api.exception;

public class BlogApiException extends RuntimeException {
    public BlogApiException() {
    }

    public BlogApiException(String message) {
        super(message);
    }

    public BlogApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogApiException(Throwable cause) {
        super(cause);
    }

    public BlogApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
