package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.dto.BookingDTO;
import java.util.List;

/**
 *
 * @author Anas
 * @author Amer Shaker
 */
public interface BookingService {

    public abstract int book(BookingDTO bookingDTO);

    public abstract List<Object[]> getAllBarberBookings(int barberId);

    public abstract List<Object[]> getAllUserBookings(int userId);

    public abstract boolean cancelBooking(int bookingId);
}
