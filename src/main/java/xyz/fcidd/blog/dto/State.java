package xyz.fcidd.blog.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum State implements Serializable {
    SUCCESS(200, "操作成功"),
    ERR_BASE64_NULL_TEXT(401, "Base64加密的文本为空"),
    ERR_NULL_BASE64(402, "Base64编码为空"),
    ERR_BASE64(403, "Base64编码无效"),
    ERR_UNKNOWN(900, "未知错误");

    private final int code;
    private final String message;

    State(int code, String message) {
        this.code = code;
        this.message = message;
    }

}