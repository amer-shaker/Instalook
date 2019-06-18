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

        for (Salon s : salons) {
            System.out.println(s.toString());
        }

        session.getTransaction().commit();
        return salons;
    }

    @Override
    public long getSalonRate(int salonId) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "select sum(rate), count(*) from Barber where salon.salonId= :id";
        Query query = session.createQuery(hql).setParameter("id", salonId);
        List listResult = query.list();

        // sum
        Object result[] = (Object[]) listResult.get(0);
        Long sumRes1ult = (Long) result[0];
        System.out.println("sum: " + sumRes1ult);
        long sum = sumRes1ult.longValue();

        //count
        Long countResiult = (Long) result[1];
        System.out.println("count: " + countResiult);
        long count = countResiult.longValue();

        return (sum / count);
    }
}
