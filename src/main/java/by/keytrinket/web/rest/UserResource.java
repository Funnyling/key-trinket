package by.keytrinket.web.rest;

import by.keytrinket.security.CurrentUser;
import by.keytrinket.security.UserDetails;
import by.keytrinket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ntishkevich
 */
@RestController
@RequestMapping(path = "/api/users")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/current")
    public ResponseEntity user(@CurrentUser UserDetails userDetails) {
        log.debug("REST request to get current user");
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @RequestMapping(path = "/{login}")
    public ResponseEntity user(@PathVariable String login) {
        log.debug("REST request to get User : {}", login);
//        userService.getUserWithAuthoritiesByLogin(login)
//                .map(user -> new UserDTO(user))
//                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND))
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
