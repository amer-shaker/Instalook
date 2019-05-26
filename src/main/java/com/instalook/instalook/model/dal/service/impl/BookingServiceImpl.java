/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dao.BookingDAO;
import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Booking;

import com.instalook.instalook.model.dal.service.BookingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author abdullah
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Override
    public List<Object[]> getBarberBookings(int barberId) {
        return bookingDAO.getBarberBookings(barberId);
    }

}
