package springboot.kakao_boot_camp.security.CustomSecurity.Context;

public class CustomSecurityContextHolder {
    private static final ThreadLocal<CustomSecurityContext> CONTEXT = new ThreadLocal<>();

    public static void setContext(CustomSecurityContext context) {
        CONTEXT.set(context);
    }

    public static CustomSecurityContext getContext() {
        CustomSecurityContext context = CONTEXT.get();
        if (context == null) {
            context = new CustomSecurityContext();
            CONTEXT.set(context);
        }
        return context;
    }

}
