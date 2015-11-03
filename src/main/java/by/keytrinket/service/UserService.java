package by.keytrinket.service;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: ntishkevich
 */
public interface UserService extends UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {
}
