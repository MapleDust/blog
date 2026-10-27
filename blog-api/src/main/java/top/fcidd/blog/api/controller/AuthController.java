package top.fcidd.blog.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.fcidd.blog.api.Service.UserService;
import top.fcidd.blog.api.dto.*;
import top.fcidd.blog.api.vo.TokenVO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    // 注册，发送激活链接
    @PostMapping("/register")
    public Result<Void> register(@Valid @RequestBody RegisterDTO dto) {
        userService.registerAndSendEmail(dto);
        return Result.success();
    }
    // 登录
    @PostMapping("/login")
    public Result<TokenVO> login(@RequestBody @Valid LoginDTO dto) {
        TokenVO tokenVO = userService.login(dto);
        return Result.success(tokenVO);
    }
    // 登出
    @PostMapping("/logout")
    public Result<Void> logout(@RequestBody @Valid LogoutDTO logoutDTO) {
        userService.logout(logoutDTO.getToken());
        return Result.success(null);
    }
    // 激活链接回调

    @GetMapping("/activate")
    public Result<String> activate(@RequestParam String token) {
        userService.activateUser(token);
        return Result.success("邮箱激活成功");
    }
    // 管理员：启用/禁用账户

    @PutMapping("/admin/user/enabled")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result<Void> changeEnabled(@RequestBody @Valid UserEnabledDTO dto) {
        userService.changeUserEnabled(dto.getUserUuid(), dto.getEnabled());
        return Result.success();
    }
}