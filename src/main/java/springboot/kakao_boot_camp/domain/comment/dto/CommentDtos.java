package springboot.kakao_boot_camp.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import springboot.kakao_boot_camp.domain.comment.entity.Comment;
import springboot.kakao_boot_camp.global.dto.CursorInfo;
import springboot.kakao_boot_camp.global.dto.PageInfo;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDtos {

    // Todo. 각 리코드 분리 필요
    // -- C --
    public record CommentCreateReq(
            Long parentId,

            @NotBlank(message = "댓글 내용은 비워둘 수 없습니다.")
            @Size(max = 1000, message = "댓글은 최대 1000자까지 입력 가능합니다.")
            String content
    ) {
    }
    public record CommentCreateRes(
            Long commentId
    ) {
        public static CommentCreateRes from(Comment comment) {
            return new CommentCreateRes(
                    comment.getId()
            );
        }
    }

    // -- R --
    public record CommentListRes(
        List<CommentSummary> comments,
        PageInfo pageInfo
    ) {
        public static CommentListRes of(List<CommentSummary> comments, PageInfo pageInfo) {
            return new CommentListRes(comments, pageInfo);
        }

        // 📝 댓글 요약 DTO
        public record CommentSummary(
                Long commentId,
                String nickname,
                String profileImageUrl,
                String content,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) {
            public static CommentSummary of(
                    Long commentId,
                    String nickname,
                    String profileImageUrl,
                    String content,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt
            ) {
                return new CommentSummary(commentId, nickname, profileImageUrl, content, createdAt, updatedAt);
            }
        }
        }
    public record CommentDetailRes(
        Long commentId,
        Long postId,
        Long userId,
        String nickname,
        String profileImageUrl,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CommentDetailRes of(Comment comment) {
        return new CommentDetailRes(
                comment.getId(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getUser().getNickName(),
                comment.getUser().getProfileImage(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}

    // -- U --
    public record CommentUpdateReq(
            String content
    ) { }
    public record CommentUpdateRes(
            Long commentId,
            String content,
            String nickname,
            String profileImage,
            LocalDateTime updatedAt
    ) {
        public static CommentUpdateRes of(Comment comment) {
            return new CommentUpdateRes(
                    comment.getId(),
                    comment.getContent(),
                    comment.getUser().getNickName(),
                    comment.getUser().getProfileImage(),
                    comment.getUpdatedAt()
            );
        }
    }

    // -- D --
    public record CommentDeleteRes(
        Long commentId,
        LocalDateTime deletedAt
) {
        public static CommentDeleteRes of(Long commentId, LocalDateTime deletedAt) {
            return new CommentDeleteRes(commentId, deletedAt);
        }
    }



}
