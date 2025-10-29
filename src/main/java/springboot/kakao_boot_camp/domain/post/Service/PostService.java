package springboot.kakao_boot_camp.domain.post.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.kakao_boot_camp.domain.post.dto.PostDtos.*;
import springboot.kakao_boot_camp.domain.post.entity.Post;
import springboot.kakao_boot_camp.domain.post.exception.PostNotFoundException;
import springboot.kakao_boot_camp.domain.post.repository.PostRepository;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.domain.user.exception.UserNotFoundException;
import springboot.kakao_boot_camp.domain.user.repository.UserRepository;
import springboot.kakao_boot_camp.global.dto.CursorInfo;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

     // -- C --
    public PostCreateRes createPost(PostCreateReq req) {

        Long userId = 1L;
        User user =userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);


        Post post = new Post();

        post.setTitle(req.title());
        post.setUser(user);
        post.setContent(req.content());
        post.setImageUrl(req.imageUrl());
        post.setCratedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        Post saved = postRepository.save(post);

        return PostCreateRes.from(saved);
    }


    // -- R --
    @Transactional
    public PostDetailRes getPostDetail(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        return PostDetailRes.from(post);

    }
    @Transactional(readOnly = true)
    public PostListRes getPostList(Long cursor) {
        int size = 10; // 한 번에 가져올 게시글 수

        // 첫 요청(cursor == 0)이면 전체 중 최신순
        List<Post> posts = (cursor == 0)
                ? postRepository.findTopNByOrderByIdDesc(size + 1)
                : postRepository.findTopNByIdLessThanOrderByIdDesc(cursor, size + 1);

        // 다음 커서 계산
        boolean hasNext = posts.size() > size;
        Long nextCursor = hasNext ? posts.get(size - 1).getId() : null;

        // 마지막 요소는 다음 페이지용 데이터이므로 제외
        List<Post> limitedPosts = hasNext ? posts.subList(0, size) : posts;

        // 📦 DTO 변환
        List<PostListRes.PostSummary> postSummaries = limitedPosts.stream()
                .map(post -> PostListRes.PostSummary.of(
                        post.getId(),
                        post.getTitle(),
                        post.getUser().getNickName(),
                        post.getUser().getProfileImage(),
                        post.getLikeCount(),
                        post.getCommentCount(),
                        post.getViewCount(),
                        post.getCratedAt(),
                        post.getUpdatedAt()
                ))
                .toList();

        // 📍 페이지 정보 생성
        CursorInfo pageInfo = CursorInfo.of(hasNext, nextCursor, size);

        return PostListRes.of(postSummaries, pageInfo);
    }


    // -- U --
    @Transactional
    public PostUpdateRes updatePost(Long postId, PostUpdateReq req) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 더티 체킹
        if (req.title() != null)     post.setTitle(req.title());
        if (req.content() != null)   post.setContent(req.content());
        if (req.imageUrl() != null)  post.setImageUrl(req.imageUrl());


        return PostUpdateRes.from(post);
    }


    // -- D --
    @Transactional
    public PostDeleteRes deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        postRepository.delete(post); // 실제 삭제

        return PostDeleteRes.from(postId, LocalDateTime.now());

    }


}
