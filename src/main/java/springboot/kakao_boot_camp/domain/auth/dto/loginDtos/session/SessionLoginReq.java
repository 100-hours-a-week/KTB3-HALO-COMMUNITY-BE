package springboot.kakao_boot_camp.domain.auth.dto.loginDtos.session;

public record SessionLoginReq(
        String email,
        String passWord
) {
}
