package springboot.kakao_boot_camp.domain.post.dto.base;

import springboot.kakao_boot_camp.domain.post.entity.Post;

import java.time.LocalDateTime;

public record PostCreateRes(
        Long postId,
        String title,
        String content,
        String imageUrl,

        LocalDateTime createdAt
) {
    public static PostCreateRes from(Post post) {
        return new PostCreateRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getImageUrl(),
                post.getCreatedAt()
        );
    }
}
