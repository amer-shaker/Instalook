package com.instalook.instalook.view.controller.salon;

import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.User;
import com.instalook.instalook.model.dal.service.SalonService;
import com.instalook.instalook.view.controller.utils.BaseResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Ahmed moatasem
 * @author Amer Shaker
 */
@RestController
@RequestMapping("/salon")
public class SalonRestController {

    @Autowired
    private SalonService salonService;

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = "application/json")
    public Object login(@RequestParam("salonEmail") String email, @RequestParam("salonPassword") String password) {
        Salon salon = salonService.login(email, password);
        BaseResponse responseBody = new BaseResponse();

        if (salon != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return salon;
        } else {
            ResponseEntity<BaseResponse> response = new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
            responseBody.setStatusCode(response.getStatusCode());
            responseBody.setStatusMessage("Incorrect credentials");
            return response;
        }
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<BaseResponse> register(@RequestBody Salon salon, UriComponentsBuilder ucBuilder) {
        int id = salonService.register(salon);
        BaseResponse responseBody = new BaseResponse();

        if (id != 0) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setLocation(ucBuilder.path("/register/{id}")
                    .buildAndExpand(salon.getSalonId()).toUri());

            ResponseEntity<BaseResponse> response = new ResponseEntity<>(responseBody,
                    headers,
                    HttpStatus.CREATED);

            responseBody.setStatusCode(response.getStatusCode());
            responseBody.setStatusMessage("Successfully, created.");
            return response;
        } else {
            ResponseEntity<BaseResponse> response = new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
            responseBody.setStatusCode(response.getStatusCode());
            responseBody.setStatusMessage("Duplicate entry for e-mail");
            return response;
        }
    }

    @RequestMapping(value = "/getSalons", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Salon> getSalons() {
        List<Salon> salonsList = salonService.getAllSalons();
        return salonsList;
    }

    @RequestMapping(value = "/getSalonById",
            method = RequestMethod.POST,
            produces = "application/json")
    public Salon getSalonById(@RequestParam("salonId") Integer salonId) {
        return salonService.getSalonById(salonId);
    }
}
