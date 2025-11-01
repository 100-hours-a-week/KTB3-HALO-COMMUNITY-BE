package springboot.kakao_boot_camp.domain.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.kakao_boot_camp.domain.post.Service.PostService;
import springboot.kakao_boot_camp.domain.post.dto.PostDtos.*;
import springboot.kakao_boot_camp.global.api.ApiResponse;
import springboot.kakao_boot_camp.global.api.SuccessCode;
import springboot.kakao_boot_camp.security.CustomAuthUser;
import springboot.kakao_boot_camp.security.CustomSecurity.Context.CustomSecurityContextHolder;
import springboot.kakao_boot_camp.security.CustomSecurity.authentication.CustomAuthentication;

@RestController
@RequestMapping("/api/v1/custom/posts")
@RequiredArgsConstructor
public class PostControllerCustomSession {
    private final PostService postService;


    // -- C --
    @PostMapping
    public ResponseEntity<ApiResponse<PostCreateRes>> create(@RequestBody @Valid PostCreateReq req) {
        CustomAuthentication auth = CustomSecurityContextHolder.getContext().getAuthentication();
        Long currentUserId = auth.getUserId();
        PostCreateRes res = postService.createPost(currentUserId,req);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(SuccessCode.POST_CREATE_SUCCESS, res));
    }
}
