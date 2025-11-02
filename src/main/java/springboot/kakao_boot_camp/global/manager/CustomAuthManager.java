package springboot.kakao_boot_camp.global.manager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CustomAuthManager {
    public void create(HttpServletRequest httpServletRequest, Object object);
    public void delete(HttpServletRequest request, HttpServletResponse response);
}
