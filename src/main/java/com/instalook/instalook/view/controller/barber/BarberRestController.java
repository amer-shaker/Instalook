package com.instalook.instalook.view.controller.barber;

import com.instalook.instalook.model.dal.dto.BarberDTO;
import com.instalook.instalook.model.dal.entity.Barber;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.instalook.instalook.model.dal.service.BarberService;

/**
 *
 * @author Aya Wageeh
 */
@RestController
@RequestMapping("/barber")
public class BarberRestController {

    @Autowired
    private BarberService barberService;

    /**
     * Gets all barbers in the salon
     *
     * @param salonId is the salon id of type Integer
     *
     */
    @RequestMapping(value = "/all")
    public List<Barber> getAllBarbers(@RequestParam("salonId") int salonId) {
        return barberService.getAllBarbers(salonId);
    }

    /**
     * Gets barber in the salon by id
     *
     * @param salonId is the salon id of type Integer
     *
     */
    @RequestMapping(value = "/barber", method = RequestMethod.GET, produces = "application/json")
    public Barber getBarberById(@RequestParam("salonId") int salonId) {
        return barberService.getBarberById(salonId);
    }

    /**
     * Adds barber
     *
     * @param barber is a JSON object of Barber
     *
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public int addBarber(@RequestBody BarberDTO barber) {
        return barberService.addBarber(barber);
    }

    /**
     * Rates barber
     *
     * @param barberId is the barber id of type Integer
     * @param rate is the the new rate of type Integer
     *
     */
    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public void rateBarber(@RequestParam("barberId") int barberId, @RequestParam("rate") int rate, @RequestParam("salonId") int salonId) {
        barberService.rateBarber(barberId, rate, salonId);
    }

    /**
     * Updates barber data
     *
     * @param barber is the updated barber object of type Barber
     *
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public void updateBarberData(@RequestBody Barber barber) {
        barberService.updateBarberData(barber);
    }

    /**
     * Updates Barber Availability
     *
     * @param barberId is the barber id of type Integer
     * @param availability is the new availability of the user of type Integer
     *
     */
    @RequestMapping(value = "/available", method = RequestMethod.POST)
    public void updateBarberAvailability(@RequestParam("salonId") int barberId, @RequestParam("isAvailable") int availability) {
        barberService.updateBarberAvailability(barberId, availability);
    }

    /**
     * Deletes barber
     *
     * @param barberId is the barber id of type Integer
     *
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean deleteBarber(@RequestParam("barberId") int barberId) {
        return barberService.deleteBarberById(barberId);
    }
}
