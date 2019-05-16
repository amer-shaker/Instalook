/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.view.controller.booking;


import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Booking;
import com.instalook.instalook.model.dal.service.BookingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abdullah
 */
@RestController
public class BookingRestController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/getBookings", method = RequestMethod.GET,
            produces = "application/json")
    public List<Object[]> getBookings() {
        return bookingService.getBookings();
    }
}
