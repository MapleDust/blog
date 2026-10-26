package top.fcidd.blog.api.vo;

import lombok.Data;

@Data
public class TokenVO {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiresIn;
}