package by.keytrinket.security;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is throw in case of a not activated user trying to authenticate.
 *
 * @author ntishkevich
 */
public class UserNotActivedException extends AuthenticationException {
    public UserNotActivedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotActivedException(String msg) {
        super(msg);
    }
}
