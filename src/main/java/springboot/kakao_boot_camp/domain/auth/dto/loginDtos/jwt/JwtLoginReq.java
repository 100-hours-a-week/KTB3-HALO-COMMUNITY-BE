package springboot.kakao_boot_camp.domain.auth.dto.loginDtos.jwt;

public record JwtLoginReq(
        String email,
        String passWord
) {
}
