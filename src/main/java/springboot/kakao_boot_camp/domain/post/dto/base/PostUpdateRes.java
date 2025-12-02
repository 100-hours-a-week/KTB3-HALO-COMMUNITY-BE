package springboot.kakao_boot_camp.domain.post.dto.base;

import springboot.kakao_boot_camp.domain.post.entity.Post;

import java.time.LocalDateTime;

public record PostUpdateRes(            // 수정하고 바로 화면에 반영하기 위해 전체 정보 내려줌
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
    public static PostUpdateRes from(Post post) {
        return new PostUpdateRes(
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
