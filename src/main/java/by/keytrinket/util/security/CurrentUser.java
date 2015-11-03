package by.keytrinket.util.security;

import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * @author: ntishkevich
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
