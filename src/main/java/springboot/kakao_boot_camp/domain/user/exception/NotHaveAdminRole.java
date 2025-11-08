package springboot.kakao_boot_camp.domain.user.exception;

import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class NotHaveAdminRole extends BusinessException {
    public NotHaveAdminRole(){
        super(ErrorCode.NOT_HAVE_ADMIN_ROLE);
    }
}
