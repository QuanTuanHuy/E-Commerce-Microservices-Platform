package hust.project.cartservice.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@Slf4j
public class AuthenticationUtils {
    public static Long getCurrentUserId() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        log.info("user id: {}", jwt.getSubject());
        return Long.parseLong(jwt.getSubject());
    }
}
