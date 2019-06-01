package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.dto.BookingDTO;
import java.util.List;

/**
 *
 * @author abdullah
 */
public interface BookingService {

    /**
     *
     * @param barber
     * @return
     */
    public abstract List<Object[]> getBarberBookings(int barberId);

    public abstract List<Object[]> getUserBookings(int userId);

    public abstract boolean cancelBooking(int bookingId);

    public Integer insertNewBooking(BookingDTO bookingDTO);
}
