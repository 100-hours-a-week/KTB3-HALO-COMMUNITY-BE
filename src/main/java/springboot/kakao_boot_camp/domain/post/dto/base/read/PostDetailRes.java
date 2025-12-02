package springboot.kakao_boot_camp.domain.post.dto.base.read;

import springboot.kakao_boot_camp.domain.post.entity.Post;

import java.time.LocalDateTime;

public record PostDetailRes(
        Long postId,
        String title,
        String content,
        String imageUrl,

        int likeCount,
        int viewCount,
        int commentCount,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostDetailRes from(Post post) {
        return new PostDetailRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getImageUrl(),
                post.getLikeCount(),
                post.getViewCount(),
                post.getCommentCount(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

}
