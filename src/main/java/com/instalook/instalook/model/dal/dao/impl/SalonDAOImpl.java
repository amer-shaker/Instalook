package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.SalonDAO;
import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmed moatasem
 * @author Amer Shaker
 */
@Repository
@Transactional
public class SalonDAOImpl implements SalonDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    Session session1;

    @Override
    public Salon login(String email, String password) {
        Session session = null;
        Salon salon = null;

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Salon.class)
                    .add(Restrictions.eq("salonEmail", email))
                    .add(Restrictions.eq("salonPassword", password));
            salon = (Salon) criteria.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        } finally {
            /*if (session != null) {
                session.clear();
                session.close();
            }*/
        }

        return salon;
    }

    @Override
    public int register(Salon salon) {
        Session session = null;
        int id = 0;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            id = (Integer) session.save(salon);
            System.out.println(id);
            session.getTransaction().commit();
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        } finally {
//            if (session != null) {
//                session.clear();
//                session.close();
//            }
        }

        return id;
    }

    @Override
    public List<Salon> getAllSalons() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Salon> salons = session.createCriteria(Salon.class).list();

        for (Salon s : salons) {
            System.out.println(s.toString());
        }

        session.getTransaction().commit();
        return salons;

    }

    @Override
    public Salon getSalonById(int salonId) {

        if (session1 == null) {
            session1 = sessionFactory.openSession();
        }

        Salon salon = (Salon) session1.load(Salon.class, salonId);
        Criteria criteria = session1.createCriteria(Salon.class, "s")
                .add(Restrictions.eq("salonId", salon.getSalonId()));
        return (Salon) criteria.uniqueResult();
    }
}
