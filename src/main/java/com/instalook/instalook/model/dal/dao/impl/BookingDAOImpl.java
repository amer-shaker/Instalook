/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.BookingDAO;
import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Booking;
import java.util.AbstractList;
import java.util.ArrayList;
import static java.util.Collections.list;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public List<Object[]> getBookings(Barber barber) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        String queryString = "FROM Salon s, User u, Barber b, Booking bo WHERE u.userId = bo.user AND b.barberId = bo.barbers AND  b.salon = s.salonId AND b.barberId = 1";
//        Query query = session.createQuery(queryString);
//        List<Object[]> result = query.list();
//
//        List<Object[]> bookings = result;
//        return bookings;
//        return session.createCriteria(Users.class)
//                .createCriteria("bookingses").list();
//        userCriteria.setFetchMode("Booking", FetchMode.JOIN);
//        String queryString = "C.c_id, CH.c_des, S.s_num, CT.qty FROM CUSTOMER C, CUST_HEADER CH, SHOP S, CUST_TEMPLATE CT WHERE C.c_id = S.c_id AND C.c_id=CH.c_id AND CH.c_id=S.c_id AND CH.c_id=CT.c_id";
//        Query query = session.createQuery(queryString);
        //List<Object[]> result = query.list();

        // return userCriteria.list();
//        Criteria salonCriteria = session.createCriteria("Salons", "s");
        Criteria barberCriteria = session.createCriteria(Barber.class).createCriteria("salon", "s");
        return barberCriteria.list();
//        Criteria bookingCriteria = session.createCriteria("Booking", "bo");
//        barberCriteria.add(Restrictions.eq("barber_id", barber.getBarberId()));
//        userCriteria.add(Restrictions.eq("bo.user_id", ));
//        barberCriteria.add(Restrictions.eq("barber_id", barber.getBarberId()));
//
//        criteria.createCriteria("", Criteria) //        criteria.setFetchMode("Barbers", FetchMode.JOIN);
        //        List list = criteria.list();
        //        Criteria cb = session.createCriteria(Bookings.class);
        //        cb.add(Restrictions.idEq(barber));
        //        return cb.list();
    }

}
