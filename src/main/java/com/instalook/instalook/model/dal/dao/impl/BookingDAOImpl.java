/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.BookingDAO;
import com.instalook.instalook.model.dal.dto.BookingDTO;
import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Booking;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.User;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abdullah
 */
@Repository
@Transactional
public class BookingDAOImpl implements BookingDAO {

    Session session;

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public List<Object[]> getBarberBookings(int barberId) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("select salon.salonName as salonName,"
                + " concat(user.firstName, ' ', user.lastName) as userName ,user.profilePicture as userImage ,"
                + " concat(barber.firstName,' ',barber.lastName)as barberName,booking.bookingDateTime as time, barber.barberPicture as barberImage"
                + " from Salon salon, User user, Barber barber, Booking booking"
                + " where (user.userId = booking.user.userId and barber.barberId = booking.barbers.barberId) "
                + " and (barber.salon.salonId = salon.salonId) and (barber.barberId = :currentBarberId)").setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setParameter("currentBarberId", barberId);
        List<Object[]> list = query.list();
        session.close();

        return list;
    }

    @Override
    public List<Object[]> getUserBookings(int userId) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("select booking.bookingId as bookingId, salon.salonName as salonName,"
                + " concat(barber.firstName,' ',barber.lastName)as barberName,booking.bookingDateTime as time"
                + " from Salon salon, User user, Barber barber, Booking booking"
                + " where (user.userId = booking.user.userId and barber.barberId = booking.barbers.barberId) "
                + " and (barber.salon.salonId = salon.salonId) and (user.userId = :currentUserId)").setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setParameter("currentUserId", userId);
        List<Object[]> list = query.list();
        session.close();
        return list;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        int numberOfRowsAffected = session.createQuery("delete Booking where bookingId = :id ").
                setParameter("id", bookingId).executeUpdate();
        session.getTransaction().commit();

        session.close();
        if (numberOfRowsAffected > 0) {
            return true;
        }
        return false;

    }

    @Override
    public Integer insertNewBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
       // booking.setBookingId(6);
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, bookingDTO.getUserId());
        Barber barber = (Barber) session.load(Barber.class, bookingDTO.getBarberId());
        booking.setUser(user);
        booking.setBarbers(barber);
        booking.setBookingDateTime(bookingDTO.getDate());
        Integer id = (Integer) session.save(booking);
        return id;

    }

}
