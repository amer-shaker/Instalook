/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.view.controller.authentication;

import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.service.BarbersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aya
 */
@RestController
public class BarbersRestController {

    @Autowired
    private BarbersService barbersService;

    /**
     * Gets all barbers in the salon
     *
     * @param salonId is the salon id of type Integer
     *
     */
    @RequestMapping(value = "/barbers/all")
    public List<Barber> getAllBarbers(@RequestParam("id") Integer salonId) {
        return barbersService.getAllBarbers(salonId);
    }

    /**
     * Gets barber in the salon by id
     *
     * @param id is the salon id of type Integer
     *
     */
    @RequestMapping(value = "/barbers/barber", method = RequestMethod.GET, produces = "application/json")
    public Barber getBarberById(@RequestParam("id") Integer id) {
        return barbersService.getBarberById(id);
    }

    /**
     * Adds barber
     *
     * @param barber is a JSON object of Barber
     *
     */
    @RequestMapping(value = "/barbers/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public int addBarber(@RequestBody Barber barber) {
        return barbersService.addBarber(barber);
    }

    /**
     * Rates barber
     *
     * @param barberId is the barber id of type Integer
     * @param rate is the the new rate of type Integer
     *
     */
    @RequestMapping(value = "/barbers/barber/rate", method = RequestMethod.POST)
    public void rateBarber(@RequestParam("id") Integer barberId, @RequestParam("rate") Integer rate) {
        barbersService.rateBarber(barberId, rate);
    }

    /**
     * Updates barber data
     *
     * @param barber is the updated barber object of type Barber
     *
     */
    @RequestMapping(value = "/barbers/barber/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public void updateBarberData(@RequestBody Barber barber) {
        barbersService.updateBarberData(barber);
    }

    /**
     * Updates Barber Availability
     *
     * @param barberId is the barber id of type Integer
     * @param availability is the new availability of the user of type Integer
     *
     */
    @RequestMapping(value = "/barbers/barber/available", method = RequestMethod.POST)
    public void updateBarberAvailability(@RequestParam("id") Integer barberId, @RequestParam("isAvailable") Integer availability) {
        barbersService.updateBarberAvailability(barberId, availability);
    }

    /**
     * Deletes barber
     *
     * @param barberId is the barber id of type Integer
     *
     */
    @RequestMapping(value = "/barbers/barber/delete", method = RequestMethod.DELETE)
    public int deleteBarber(@RequestParam("id") Integer barberId) {
        return barbersService.deleteBarber(barberId);
    }

}
