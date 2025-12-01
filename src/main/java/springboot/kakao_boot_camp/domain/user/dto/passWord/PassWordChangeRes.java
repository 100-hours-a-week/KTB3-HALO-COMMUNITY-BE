package springboot.kakao_boot_camp.domain.user.dto.passWord;

import jakarta.validation.constraints.NotNull;

public record PassWordChangeRes(

        @NotNull
        String nowPassWord,

        @NotNull
        String newPassWord,

        @NotNull
        String confirmPassword
) {
    public static PassWordChangeRes from(String nowPassWord, String newPassWord, String confirmPassword){
        return new PassWordChangeRes(nowPassWord, newPassWord, confirmPassword);
    }
}
