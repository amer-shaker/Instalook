package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.SalonDAO;
import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Amer Shaker
 * @author Ahmed moatasem
 */
@Repository
@Transactional
public class SalonDAOImpl implements SalonDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

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
            if (session != null) {
                //session.close();
            }
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
    public Salon getSalonById(int salonId) {
        Session session = null;
        Salon salon = null;

        try {
            session = sessionFactory.openSession();
            salon = (Salon) session.get(Salon.class, salonId);
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
    public List<Salon> getAllSalons() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Salon> salons = session.createCriteria(Salon.class).list();
        session.getTransaction().commit();
        return salons;
    }

    @Override
    public int getSalonRateById(int salonId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "select sum(rate), count(*) from Barber where salon.salonId= :id";
        Query query = session.createQuery(hql).setParameter("id", salonId);
        List listResult = query.list();
        Object result[] = (Object[]) listResult.get(0);

        // sum
        Integer sum = (Integer) result[0];
        System.out.println("sum: " + sum);

        // count
        Integer count = (Integer) result[1];
        System.out.println("count: " + count);

        return (sum / count);
    }

    @Override
    public int deleteSalonById(int salonId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String query = "delete Salon where salonId = :id";
        int result = session.createQuery(query).setParameter("id", salonId).executeUpdate();
        transaction.commit();
        session.close();
        return result;
    }
}
