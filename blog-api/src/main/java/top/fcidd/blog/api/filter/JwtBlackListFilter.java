package top.fcidd.blog.api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtBlackListFilter extends OncePerRequestFilter {
    private final StringRedisTemplate redisTemplate;
    private static final String TOKEN_BLACKLIST_PREFIX = "token:blacklist:";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            Boolean isBlack = redisTemplate.hasKey(TOKEN_BLACKLIST_PREFIX + token);
            if (Boolean.TRUE.equals(isBlack)) {
                throw new BadCredentialsException("token已失效，请重新登录");
            }
        }
        filterChain.doFilter(request, response);
    }
}