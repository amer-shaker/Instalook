/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.BookingDAO;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author abdullah
 */
@Repository
@Transactional
public class BookingDAOImpl implements BookingDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public List<Object[]> getBarberBookings(int barberId) {
        Query query = sessionFactory.openSession().createQuery("select salon.salonName as salonName,"
                + " concat(user.firstName, ' ', user.lastName) as userName ,user.profilePicture as userImage ,"
                + " concat(barber.firstName,' ',barber.lastName)as barberName,booking.bookingDateTime as time, barber.barberPicture as barberImage"
                + " from Salon salon, User user, Barber barber, Booking booking"
                + " where (user.userId = booking.user.userId and barber.barberId = booking.barbers.barberId) "
                + " and (barber.salon.salonId = salon.salonId) and (barber.barberId = :currentBarberId)").setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setParameter("currentBarberId", barberId);
       
        return query.list();
    }

}
