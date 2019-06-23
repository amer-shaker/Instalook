package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.SalonDAO;
import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Amer Shaker
 */
@Repository
public class SalonDAOImpl implements SalonDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public int register(Salon salon) {
        int salonId = 0;

        try {
            session = sessionFactory.getCurrentSession();
         
            salonId = (Integer) session.save(salon);
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
            session.clear();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return salonId;
    }

    @Override
    public Salon login(String email, String password) {
        Salon salon = null;

        try {
            session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(Salon.class)
                    .add(Restrictions.eq("salonEmail", email))
                    .add(Restrictions.eq("salonPassword", password));

            salon = (Salon) criteria.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return salon;
    }

    @Override
    public Salon getSalonById(int salonId) {
        Salon salon = null;

        try {
            session = sessionFactory.getCurrentSession();
            salon = (Salon) session.get(Salon.class, salonId);
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return salon;
    }

    @Override
    public double getSalonRateById(int salonId) {
        double salonRate = 0;

        try {
            session = sessionFactory.getCurrentSession();
            String hql = "select sum(rate), count(*) from Barber where salon.salonId= :id";
            Query query = session.createQuery(hql).setParameter("id", salonId);
            List listResult = query.list();
            Object result[] = (Object[]) listResult.get(0);

            // sum
            Double sum = (Double) result[0];
            System.out.println("sum: " + sum);

            // count
            Double count = (Double) result[1];
            System.out.println("count: " + count);

            salonRate = (sum / count);
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return salonRate;
    }

    @Override
    public List<Salon> getAllSalons() {
        List<Salon> salons = null;

        try {
            session = sessionFactory.getCurrentSession();
            salons = session.createCriteria(Salon.class).list();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return salons;
    }

    @Override
    public boolean deleteSalonById(int salonId) {
        boolean isSuccess = false;

        try {
            session = sessionFactory.getCurrentSession();

            Object salon = session.load(Salon.class, salonId);
            session.delete(salon);
            isSuccess = true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return isSuccess;
    }
}
