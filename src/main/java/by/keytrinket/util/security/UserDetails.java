package by.keytrinket.util.security;

import by.keytrinket.domain.User;

/**
 * @author ntishkevich
 */
public interface UserDetails extends org.springframework.security.core.userdetails.UserDetails {

    User getUser();
}
