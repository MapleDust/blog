package top.fcidd.blog.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserEnabledDTO {
    @NotBlank(message = "用户uuid不能为空")
    private String userUuid;

    @NotNull(message = "启用状态不能为空")
    private Boolean enabled;
}
