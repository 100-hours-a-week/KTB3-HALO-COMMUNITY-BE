package springboot.kakao_boot_camp.domain.user.exception;

import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class PasswordMismatchException extends BusinessException {
    public PasswordMismatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
