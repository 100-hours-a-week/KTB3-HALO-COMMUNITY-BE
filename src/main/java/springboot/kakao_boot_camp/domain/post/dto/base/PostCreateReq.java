package springboot.kakao_boot_camp.domain.post.dto.base;

import jakarta.validation.constraints.NotBlank;

// -- 게시글 생성 ==
public record PostCreateReq(
        @NotBlank(message = "제목이 비었습니다.")
        String title,

        @NotBlank(message = "내용이 비었습니다.")
        String content,

        String imageUrl
) {
}
