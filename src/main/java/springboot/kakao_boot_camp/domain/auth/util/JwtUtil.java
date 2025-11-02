package springboot.kakao_boot_camp.domain.auth.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import springboot.kakao_boot_camp.domain.auth.exception.InvalidTokenTypeException;
import springboot.kakao_boot_camp.domain.auth.exception.JwtTokenExpiredException;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.CustomAuthentication;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.principal.CustomAuthUserWithoutSpringScurity;




@Component
public class JwtUtil {

    private final SecretKey accessKey;
    private final SecretKey refreshKey;
    private final long accessTtl;
    private final long refreshTtl;

    // 생성자 주입
    public JwtUtil(
            @Value("${jwt.access.secret}") String accessSecret,
            @Value("${jwt.refresh.secret}") String refreshSecret,
            @Value("${jwt.access.expiration}") long accessTtl,
            @Value("${jwt.refresh.expiration}") long refreshTtl
    ) {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecret));
        this.accessTtl = accessTtl;
        this.refreshTtl = refreshTtl;
    }

    // Access Token 생성
    public String createAccessToken(CustomAuthentication auth) {
        return createToken(auth, accessKey, accessTtl);
    }

    // Refresh Token 생성
    public String createRefreshToken(CustomAuthentication auth) {
        return createToken(auth, refreshKey, refreshTtl);
    }

    // 공통 토큰 생성 로직
    private String createToken(CustomAuthentication auth, SecretKey key, long ttl) {
        CustomAuthUserWithoutSpringScurity user = (CustomAuthUserWithoutSpringScurity) auth.getPrincipal();

        return Jwts.builder()
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ttl))
                .signWith(key)
                .compact();
    }
    // Access Token 파싱
    public Claims extractAccessToken(String token) {
        return extractToken(token, accessKey);
    }
    // Refresh Token 파싱
    public Claims extractRefreshToken(String token) {
        return extractToken(token, refreshKey);
    }
    // 공통 파싱 로직
    private Claims extractToken(String token, SecretKey key) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new JwtTokenExpiredException();
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidTokenTypeException();
        }
    }
}



