/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.view.controller.authentication;

import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.User;
import com.instalook.instalook.model.dal.service.SalonService;
import java.util.List;
import org.jboss.logging.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 */

@RestController
public class SalonRestController {

    @Autowired
    private SalonService salonService;

    @RequestMapping(value = "/getSalons", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Salon> getSalons() {
        List<Salon> salonsList = salonService.getAllSalons();
        return salonsList;
    }
    
    @RequestMapping("/getSalonsById/{salonId}")
    public List<Salon> getSalons(@PathVariable("salonId") int id) {
        return salonService.getAllSalonsById(id);
    }
       
//    @RequestMapping(value = "/getSalonsByCategory", method = RequestMethod.GET, headers = "Accept=application/json")
//    public List<Salon> getSalonsByCategory(@Param String salonType ) {
//        List<Salon> salonsList = salonService.getAllSallonsByCategory(salonType);
//        return salonsList;
//    }

}
