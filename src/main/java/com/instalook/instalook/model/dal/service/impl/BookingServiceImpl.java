package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dao.BookingDAO;
import com.instalook.instalook.model.dal.dto.BookingDTO;
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

    @Override
    public List<Object[]> getUserBookings(int userId) {
        return bookingDAO.getUserBookings(userId);

    }

    @Override
    public boolean cancelBooking(int bookingId) {
        return bookingDAO.cancelBooking(bookingId);
    }

    @Override
    public Integer insertNewBooking(BookingDTO bookingDTO) {
        return bookingDAO.insertNewBooking(bookingDTO);
    }

}
