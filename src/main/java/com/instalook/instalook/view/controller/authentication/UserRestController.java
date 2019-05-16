package com.instalook.instalook.view.controller.authentication;

import com.instalook.instalook.model.dal.entity.Service;
import com.instalook.instalook.model.dal.entity.User;
import com.instalook.instalook.model.dal.service.SalonServicesService;
import com.instalook.instalook.model.dal.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Amer Shaker
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Void> register(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        int id = userService.register(user);
        if (id == 0) {
            HttpHeaders headers = new HttpHeaders();
            //headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setLocation(ucBuilder.path("/user/register/{id}")
                    .buildAndExpand(user.getUserId()).toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setFirstName("Admer");
        user.setLastName("Shadker");
        user.setPassword("ttttt");
        user.setEmail("ssdrrbbbbbbrrrrhjkld@ww.com");
        // userService.register(user);
        return user;
    }

}
