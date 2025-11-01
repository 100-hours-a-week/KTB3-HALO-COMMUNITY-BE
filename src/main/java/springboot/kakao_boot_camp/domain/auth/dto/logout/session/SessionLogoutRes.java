package springboot.kakao_boot_camp.domain.auth.dto.logout.session;

public record SessionLogoutRes(
        Long userId
) {
    public SessionLogoutRes(Long userId ){
        this.userId=userId;
    }
    public SessionLogoutRes from(Long uerId){
        return new SessionLogoutRes(userId);
    }
}
