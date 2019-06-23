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
 * @author Amer Shaker
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Override
    @Transactional
    public int book(BookingDTO bookingDTO) {
        return bookingDAO.book(bookingDTO);
    }

    @Override
    @Transactional
    public List<Object[]> getAllBarberBookings(int barberId) {
        return bookingDAO.getAllBarberBookings(barberId);
    }

    @Override
    @Transactional
    public List<Object[]> getAllUserBookings(int userId) {
        return bookingDAO.getAllUserBookings(userId);

    }

    @Override
    @Transactional
    public boolean cancelBooking(int bookingId) {
        return bookingDAO.cancelBooking(bookingId);
    }
}
