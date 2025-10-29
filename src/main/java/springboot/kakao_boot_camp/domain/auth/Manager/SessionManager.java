package springboot.kakao_boot_camp.domain.auth.Manager;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import springboot.kakao_boot_camp.global.constant.SessionConst;

import java.util.HashMap;
import java.util.UUID;

@Component
public class SessionManager {

    HashMap<String, Long> store = new HashMap<>();

    public void createSession(Long userId, HttpServletResponse res) {
        String token = UUID.randomUUID().toString();
        store.put(token, userId);
        Cookie cookie = new Cookie(SessionConst.sessionId, token);
        res.addCookie(cookie);
    }
}
