/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.dto.BookingDTO;
import com.instalook.instalook.model.dal.entity.Booking;
import java.util.List;

/**
 *
 * @author abdullah
 */
public interface BookingDAO {

    public abstract List<Object[]> getBarberBookings(int barberId);

    public abstract List<Object[]> getUserBookings(int userId);

    public abstract boolean cancelBooking(int bookingId);

    public Integer insertNewBooking(BookingDTO bookingDTO) ;

}
