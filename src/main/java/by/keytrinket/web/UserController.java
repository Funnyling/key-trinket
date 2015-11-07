package by.keytrinket.web;

import by.keytrinket.security.CurrentUser;
import by.keytrinket.security.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ntishkevich
 */
@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @RequestMapping(path = "/currentUser")
    public String user(@CurrentUser UserDetails userDetails) {
        return userDetails.getUsername();
    }
}
