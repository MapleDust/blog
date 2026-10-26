package top.fcidd.blog.api.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysUser {
    private Long id;
    private String uuid;
    private String username;
    private String password;
    private String email;
    private String role;
    private Boolean activated;
    private Boolean enabled;
    private String activateToken;
    private LocalDateTime tokenExpireTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}