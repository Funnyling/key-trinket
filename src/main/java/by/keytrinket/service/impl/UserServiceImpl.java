package by.keytrinket.service.impl;

import by.keytrinket.domain.User;
import by.keytrinket.repository.UserRepository;
import by.keytrinket.service.UserService;
import by.keytrinket.util.security.UserDetails;
import by.keytrinket.util.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ntishkevich
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User {username = %s} not found", username));
        }
        return new UserDetailsImpl(user);
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        User user = ((UserDetails) authenticationSuccessEvent.getAuthentication().getPrincipal()).getUser();
        Date lastLoginDate = new Date();
        user.setLastEventDate(lastLoginDate);
        userRepository.save(user);
    }
}
