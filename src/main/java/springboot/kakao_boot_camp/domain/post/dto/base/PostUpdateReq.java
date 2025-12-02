package springboot.kakao_boot_camp.domain.post.dto.base;

// -- 게시글 수정  --
public record PostUpdateReq(
        String title,
        String content,
        String imageUrl
) {
}
