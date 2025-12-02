package springboot.kakao_boot_camp.domain.post.dto.base;

public record UserProfile(
        Long id,
        String nickname,
        String profileImageUrl
) {}
