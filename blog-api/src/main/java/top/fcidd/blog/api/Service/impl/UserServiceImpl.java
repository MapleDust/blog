package top.fcidd.blog.api.Service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import top.fcidd.blog.api.Service.UserService;
import top.fcidd.blog.api.dto.LoginDTO;
import top.fcidd.blog.api.dto.RegisterDTO;
import top.fcidd.blog.api.entity.SysUser;
import top.fcidd.blog.api.mapper.SysUserMapper;
import top.fcidd.blog.api.vo.TokenVO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final SysUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Value("${app.activate.url-prefix}")
    private String activateUrlPrefix;

    // 激活链接有效期：24小时
    private static final int EXPIRE_HOUR = 24;

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final StringRedisTemplate redisTemplate;

    private static final String TOKEN_BLACKLIST_PREFIX = "token:blacklist:";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerAndSendEmail(RegisterDTO dto) {
        // 校验用户名是否存在
        SysUser existUser = userMapper.selectByUsername(dto.getUsername());
        if (existUser != null) {
            throw new RuntimeException("该账号已注册");
        }

        SysUser user = new SysUser();
        // 业务UUID
        user.setUuid(UUID.randomUUID().toString());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole("USER");
        user.setActivated(false);
        // 默认账户启用
        user.setEnabled(true);

        // 生成激活token
        String activateToken = UUID.randomUUID().toString().replace("-", "");
        user.setActivateToken(activateToken);
        user.setTokenExpireTime(LocalDateTime.now().plusHours(EXPIRE_HOUR));


        // 发送激活邮件
        int sendActivateEmail = sendActivateEmail(user.getEmail(), activateToken);
        if (sendActivateEmail == 1) {
            userMapper.insert(user);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void activateUser(String token) {
        if (!StringUtils.hasText(token)) {
            throw new RuntimeException("激活令牌不能为空");
        }
        SysUser user = userMapper.selectByActivateToken(token);
        if (user == null) {
            throw new RuntimeException("激活链接无效");
        }
        if (user.getActivated()) {
            throw new RuntimeException("该邮箱已激活，无需重复操作");
        }
        if (LocalDateTime.now().isAfter(user.getTokenExpireTime())) {
            throw new RuntimeException("激活链接已过期，请重新注册或重发邮件");
        }
        // 修改激活状态
        userMapper.updateActivated(user.getUuid(), true);
        // 清空token（可选）
        userMapper.updateActivateToken(user.getUuid(), null, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeUserEnabled(String userUuid, Boolean enabled) {
        SysUser user = userMapper.selectByUuid(userUuid);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        userMapper.updateEnabled(userUuid, enabled);
    }

    @Override
    public SysUser getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<SysUser> getUserList() {
        return userMapper.selectList();
    }

    /**
     * 发送html激活邮件
     */
    private int sendActivateEmail(String email, String token) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("账号邮箱激活");
            String link = activateUrlPrefix + "?token=" + token;
            String html = "<p>您好，请点击下面链接激活账号（24小时内有效）</p>" + "<a href=\"" + link + "\">点击激活</a>";
            helper.setText(html, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("邮件发送失败", e);
        }
        return 1;
    }

    @Override
    public TokenVO login(LoginDTO dto) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);

        Instant now = Instant.now();
        long expireSeconds = 30 * 60;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expireSeconds))
                .subject(authentication.getName())
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(a -> a.getAuthority())
                        .collect(Collectors.toList()))
                .build();

        JwtEncoderParameters encoderParams = JwtEncoderParameters.from(claims);
        Jwt jwt = jwtEncoder.encode(encoderParams);
        log.debug("生成的jwt：{}", jwt.getTokenValue()); // 打印这一行

        TokenVO tokenVO = new TokenVO();
        tokenVO.setAccessToken(jwt.getTokenValue());
        tokenVO.setTokenType("Bearer");
        tokenVO.setExpiresIn(expireSeconds);
        tokenVO.setRefreshToken(null);
        log.debug("tokenVO对象：{}", tokenVO); // 打印对象，看赋值是否成功
        return tokenVO;
    }

    @Override
    public void logout(String token) {
        // 去掉 Bearer 前缀
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        Jwt jwt = jwtDecoder.decode(token);
        Instant expireAt = jwt.getExpiresAt();
        long remainSeconds = ChronoUnit.SECONDS.between(Instant.now(), expireAt);
        // 剩余时间大于0才加入黑名单
        if (remainSeconds > 0) {
            redisTemplate.opsForValue().set(TOKEN_BLACKLIST_PREFIX + token, "1", remainSeconds, TimeUnit.SECONDS);
        }
    }

}
