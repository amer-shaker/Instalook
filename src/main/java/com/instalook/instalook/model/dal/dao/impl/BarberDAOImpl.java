package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dto.BarberDTO;
import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.instalook.instalook.model.dal.dao.BarberDAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Aya
 */
@Repository
public class BarberDAOImpl implements BarberDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public int addBarber(BarberDTO barberDTO) {
        int salonId = 0;

        try {
            session = sessionFactory.getCurrentSession();

            Salon salon = (Salon) session.load(Salon.class, barberDTO.getSalonId());
            barberDTO.getBarber().setSalon(salon);
            salon.getBarbers().add(barberDTO.getBarber());
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
    public Barber getBarberById(int barberId) {
        Barber barber = null;

        try {
            session = sessionFactory.getCurrentSession();
            barber = (Barber) session.load(Barber.class, barberId);
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return barber;
    }

    @Override
    public List<Barber> getAllBarbers(int salonId) {
        List<Barber> barbers = null;

        try {
            session = sessionFactory.getCurrentSession();

            Salon salon = (Salon) session.load(Salon.class, salonId);
            Criteria criteria = session.createCriteria(Barber.class)
                    .add(Restrictions.eq("salon", salon));

            barbers = criteria.list();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return barbers;
    }

    @Override
    public void updateBarberData(Barber barber) {
        try {
            session = sessionFactory.getCurrentSession();

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
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }
    }

    @Override
    public void updateBarberAvailability(int barberId, int availability) {
        try {
            session = sessionFactory.getCurrentSession();
            Barber barber = (Barber) session.load(Barber.class, barberId);
            barber.setIsAvailable(availability);
            session.update(barber);
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
            session.clear();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }
    }

    @Override
    public void rateBarber(int barberId, int rate, int salonId) {
        try {
            session = sessionFactory.getCurrentSession();
            Barber barber = (Barber) session.load(Barber.class, barberId);
            barber.setRate(rate);
            session.update(barber);
            updateSalonRate(salonId);
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
            session.clear();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }
    }

    public void updateSalonRate(int salonId) {
        double salonRate = 0;
        try {
            session = sessionFactory.getCurrentSession();
            String hql = "select sum(rate), count(*) from Barber where salon.salonId= :id";
            Query query = session.createQuery(hql).setParameter("id", salonId);
            List listResult = query.list();
            Object result[] = (Object[]) listResult.get(0);

            // sum
            long sum = (long) result[0];
            System.out.println("sum: " + sum);
            // count
            long count = (long) result[1];
            System.out.println("count: " + count);
            salonRate = (double) (sum / count);

            // update salon rate
            Salon updatedSalon = (Salon) session.load(Salon.class, salonId);
            System.out.println("salon name" + updatedSalon.getSalonName());
            updatedSalon.setSalonRate(salonRate);
            session.update(updatedSalon);

        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }
    }

    @Override
    public boolean deleteBarberById(int barberId) {
        boolean isSuccess = false;

        try {
            session = sessionFactory.getCurrentSession();

            String query = "delete Barber where barberId = :id";
            int result = session.createQuery(query).setParameter("id", barberId).executeUpdate();
            isSuccess = result > 0;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return isSuccess;
    }
}
