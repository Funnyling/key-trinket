package by.keytrinket.util.security.xauth;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author ntishkevich
 */
public class TokenProvider {
    public String getUserNameFromToken(String authToken) {
        return null;
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        return false;
    }
}
