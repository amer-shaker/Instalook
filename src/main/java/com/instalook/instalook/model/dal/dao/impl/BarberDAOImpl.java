package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dto.BarberDTO;
import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.instalook.instalook.model.dal.dao.BarberDAO;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Aya
 */
@Repository
@Transactional
public class BarberDAOImpl implements BarberDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public List<Barber> getAllBarbers(Integer salonId) {
        Salon currentSalon = (Salon) sessionFactory.openSession().load(Salon.class, salonId);
        Criteria criteria = sessionFactory.openSession().createCriteria(Barber.class);
        criteria.add(Restrictions.eq("salon", currentSalon));
        return criteria.list();
    }

    @Override
    public Barber getBarberById(Integer id) {
        return (Barber) sessionFactory.openSession().get(Barber.class, id);
    }

    @Override
    public int addBarber(BarberDTO barberDTO) {
        Session session = null;
        int id = 0;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Salon salon = (Salon) session.load(Salon.class, barberDTO.getSalonId());
            salon.getBarbers().add(barberDTO.getBarber());
            id = (Integer) session.save(salon);
            session.flush();
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
    public void updateBarberAvailability(Integer barberId, Integer availability) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Barber barber = (Barber) session.load(Barber.class, barberId);
        barber.setIsAvailable(availability);
        session.update(barber);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateBarberData(Barber barber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Barber updatedBarber = (Barber) session.load(Barber.class, barber.getBarberId());
        updatedBarber.setRate(barber.getRate());
        updatedBarber.setFirstName(barber.getFirstName());
        updatedBarber.setLastName(barber.getLastName());
        updatedBarber.setBarberPicture(barber.getBarberPicture());
        updatedBarber.setBookings(barber.getBookings());
        updatedBarber.setIsAvailable(barber.getIsAvailable());
        updatedBarber.setRole(barber.getRole());
        updatedBarber.setSalon(barber.getSalon());

        session.evict(updatedBarber);
        session.update(barber);
        transaction.commit();
        session.close();
    }

    @Override
    public void rateBarber(Integer barberId, Integer rate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Barber barber = (Barber) session.load(Barber.class, barberId);
        barber.setRate(rate);
        session.update(barber);
        transaction.commit();
        session.close();
    }

    @Override
    public int deleteBarber(Integer barberId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String query = "delete Barber where barberId = :id";
        int result = session.createQuery(query).setParameter("id", barberId).executeUpdate();
        transaction.commit();
        session.close();
        return result;
    }
}
