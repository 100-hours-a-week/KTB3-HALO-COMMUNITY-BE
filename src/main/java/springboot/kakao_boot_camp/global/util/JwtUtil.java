package springboot.kakao_boot_camp.global.util;


import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import springboot.kakao_boot_camp.global.config.JwtConfig;

import javax.crypto.SecretKey;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtConfig jwtConfig;

    final SecretKey accessSecretKey = jwtConfig.getAccessSecretKey();
    final SecretKey refeshSecretKey = jwtConfig.getRefreshSecretKey();

    final Long accessTokenExp = jwtConfig.getAccessSecretExp();
    final Long refreshTokenExp = jwtConfig.getRefreshSecretExp();



}
