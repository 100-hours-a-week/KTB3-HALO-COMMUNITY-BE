package springboot.kakao_boot_camp.global.manager.session;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import springboot.kakao_boot_camp.domain.user.entity.User;
import springboot.kakao_boot_camp.global.manager.CustomManager;

@Component
public class SessionManager implements CustomManager {

    @Override
    public void create(HttpServletRequest req, Object object) {
        User user = (User) object;
         HttpSession session = req.getSession();
        session.setAttribute("userId",user.getId().toString());
        session.setMaxInactiveInterval(3600);       // 세션 유효시간 설정, 단위 시간 초 ( 3600 : 1시간)
    }
}