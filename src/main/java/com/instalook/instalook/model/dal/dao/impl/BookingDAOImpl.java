/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.BookingDAO;
import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Booking;
import com.instalook.instalook.model.dal.entity.Salon;
import java.util.AbstractList;
import java.util.ArrayList;
import static java.util.Collections.list;

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
    public List<Object[]> getBookings() {
        Query query = sessionFactory.openSession().createQuery("select s.salonName as salonName,u.firstName as firstName from Salon s, User u, Barber b, Booking bo "
                + "where (u.userId = bo.user.userId and b.barberId = bo.barbers.barberId) "
                + "and (b.salon.salonId = s.salonId) and (b.barberId = :currentBarberId)").setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setParameter("currentBarberId", 3);
        return query.list();
    }

}
