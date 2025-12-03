package springboot.kakao_boot_camp.domain.post.dto.like;

public record PostLikeDto(
        Long postId,
        boolean liked
) {
    public static PostLikeDto from(Long postId, boolean liked) {
        return new PostLikeDto(postId, liked);
    }
}

