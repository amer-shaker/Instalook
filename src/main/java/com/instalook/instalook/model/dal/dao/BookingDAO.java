package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.dto.BookingDTO;
import java.util.List;

/**
 *
 * @author abdullah
 */
public interface BookingDAO {

    public abstract List<Object[]> getBarberBookings(int barberId);

    public abstract List<Object[]> getUserBookings(int userId);

    public abstract boolean cancelBooking(int bookingId);

    public Integer insertNewBooking(BookingDTO bookingDTO);
}
