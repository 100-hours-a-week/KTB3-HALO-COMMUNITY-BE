package springboot.kakao_boot_camp.domain.post.dto;

public record UserProfile(
        Long id,
        String nickname,
        String profileImageUrl
) {}
