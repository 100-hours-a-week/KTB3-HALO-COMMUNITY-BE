package springboot.kakao_boot_camp.domain.auth.Manager.login.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springboot.kakao_boot_camp.domain.auth.util.JwtUtil;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.CustomAuthentication;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.token.CustomAuthenticationToken;


@Component
@RequiredArgsConstructor
public class JwtAuthManager {
    private final JwtUtil jwtUtil;

    public TokenPair createTokens(CustomAuthentication token){
        String accessToken = jwtUtil.createAccessToken(token);
        String refreshToken = jwtUtil.createRefreshToken(token);

        return TokenPair.from(accessToken, refreshToken);
    }

    public record TokenPair(String accessToken, String refreshToken) {
        public static TokenPair from(String accessToken, String refreshToken){
            return new TokenPair(accessToken, refreshToken);
        }
    }
}