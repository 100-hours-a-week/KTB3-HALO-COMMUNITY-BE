package springboot.kakao_boot_camp.domain.post.dto;

import springboot.kakao_boot_camp.domain.user.model.User;

public record UserProfile(
        String nickname,
        String profileImageUrl
) {

    public static UserProfile from(User user) {
        return new UserProfile(user.getNickName(), user.getProfileImage());
    }

}
