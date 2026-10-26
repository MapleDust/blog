package top.fcidd.blog.api.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogoutDTO {
    @NotBlank(message = "token不能为空")
    private String token;
}
