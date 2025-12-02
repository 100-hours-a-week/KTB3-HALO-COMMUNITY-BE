package springboot.kakao_boot_camp.domain.post.dto.base.delete;

import java.time.LocalDateTime;

public record PostDeleteRes(
        Long postId,
//            boolean deleted,        // 추후 soft 삭제 시 사용
        LocalDateTime deletedAt
) {
    public static PostDeleteRes from(Long id, /*boolean deleted,*/ LocalDateTime deletedAt) {
        return new PostDeleteRes(id, /*deleted,*/ deletedAt);
    }
}
