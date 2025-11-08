package springboot.kakao_boot_camp.domain.user.exception;

import springboot.kakao_boot_camp.global.api.ErrorCode;
import springboot.kakao_boot_camp.global.exception.BusinessException;

public class WrongCurrentPasswordException extends BusinessException {
    public WrongCurrentPasswordException(){
        super(ErrorCode.WRONG_CURRENT_PASSWORD);
    }
}
