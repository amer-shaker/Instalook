package com.instalook.instalook.view.controller.authentication;

import com.instalook.instalook.view.controller.utils.Response;
import com.instalook.instalook.model.dal.entity.User;
import com.instalook.instalook.model.dal.service.UserService;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/user/register/",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<Response> register(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        int id = userService.register(user);
        Response responseBody = new Response();

        if (id != 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setLocation(ucBuilder.path("/user/register/{id}")
                    .buildAndExpand(user.getUserId()).toUri());

            ResponseEntity<Response> response = new ResponseEntity<>(responseBody,
                    headers,
                    HttpStatus.CREATED);

            responseBody.setStatusCode(response.getStatusCode());
            responseBody.setStatusMessage("Successfully, registered.");
            return response;
        } else {
            ResponseEntity<Response> response = new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
            responseBody.setStatusCode(response.getStatusCode());
            responseBody.setStatusMessage("Duplicate entry for e-mail");
            return response;
        }
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json")
    public Object login(@QueryParam("email") String email, @QueryParam("password") String password) {
        User user = userService.login(email, password);
        Response responseBody = new Response();

        if (user != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return  user;
        } else {
            ResponseEntity<Response> response = new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
            responseBody.setStatusCode(response.getStatusCode());
            responseBody.setStatusMessage("Incorrect credentials");
            return response;
        }
    }
}
