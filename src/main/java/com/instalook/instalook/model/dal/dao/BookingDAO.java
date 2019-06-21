package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.dto.BookingDTO;
import java.util.List;

/**
 *
 * @author Anas
 * @author Amer Shaker
 */
public interface BookingDAO {

    public abstract int book(BookingDTO bookingDTO);

    public abstract List<Object[]> getAllBarberBookings(int barberId);

    public abstract List<Object[]> getAllUserBookings(int userId);

    public abstract boolean cancelBooking(int bookingId);
}
