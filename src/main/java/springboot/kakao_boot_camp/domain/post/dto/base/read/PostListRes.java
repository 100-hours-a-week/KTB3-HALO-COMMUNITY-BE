package springboot.kakao_boot_camp.domain.post.dto.base.read;

import springboot.kakao_boot_camp.global.dto.CursorInfo;

import java.time.LocalDateTime;
import java.util.List;

// -- 게시글 리스트 및 단건 조회  --
public record PostListRes(
        List<PostSummary> posts,
        CursorInfo pageInfo
) {
    public static PostListRes of(List<PostSummary> posts, CursorInfo pageInfo) {
        return new PostListRes(posts, pageInfo);
    }

    // 📝 게시글 요약 정보
    public record PostSummary(
            Long postId,
            String title,
            String nickname,
            String profileImageUrl,

            int likeCount,
            int commentCount,
            int viewCount,

            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        public static PostSummary of(
                Long postId,
                String title,
                String nickname,
                String profileImageUrl,
                int likeCount,
                int commentCount,
                int viewCount,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) {
            return new PostSummary(
                    postId,
                    title,
                    nickname,
                    profileImageUrl,
                    likeCount,
                    commentCount,
                    viewCount,
                    createdAt,
                    updatedAt
            );
        }
    }


}
