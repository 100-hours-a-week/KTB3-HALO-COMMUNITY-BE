package springboot.kakao_boot_camp.global.manager.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.global.manager.CustomAuthManager;

@Component
public class SessionAuthManager implements CustomAuthManager {

    @Override
    public void create(HttpServletRequest req, Object object) {
        User user = (User) object;
         HttpSession session = req.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole().name());
        session.setMaxInactiveInterval(3600);       // 세션 유효시간 설정, 단위 시간 초 ( 3600 : 1시간)
    }
}