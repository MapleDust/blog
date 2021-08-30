package xyz.fcidd.blog.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class R<T> implements Serializable {
    //状态码
    private int state;
    //消息
    private String message;
    //数据
    private T data;

    /**
     * 操作成功
     *
     * @return 将状态码标记为成功
     */
    public static R ok() {
        return new R().setState(State.SUCCESS);
    }

    /**
     * 操作成功，且相应数据
     *
     * @param data 需要响应到客户端的数据
     * @param <T>  响应到客户端数据的类型
     * @return 操作成功且封装了相应数据的对象
     */
    public static <T> R ok(T data) {
        return R.ok().setData(data);
    }

    /**
     * 操作失败
     *
     * @param state 操作失败状态码
     * @param e     操作失败并捕获异常的对象
     * @return 已经封装了操作失败的状态码，并返回错误信息
     */
    public static R failure(int state, Throwable e) {
        return new R().setState(state).setMessage(e.getMessage());
    }

    public interface State {
        /**
         * 成功
         */
        int SUCCESS = 200;
        /**
         * Base64加密的文本为空
         */
        int ERR_BASE64_NULL_TEXT = 401;
        /**
         * Base64编码为空
         */
        int ERR_NULL_BASE64=402;
        /**
         * Base64编码无效
         */
        int ERR_BASE64=403;
        /**
         * 未知错误
         */
        int ERR_UNKNOWN = 900;
    }
}

