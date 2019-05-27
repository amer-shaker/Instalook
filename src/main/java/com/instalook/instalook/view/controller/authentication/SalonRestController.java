package com.instalook.instalook.view.controller.authentication;

import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.service.SalonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}