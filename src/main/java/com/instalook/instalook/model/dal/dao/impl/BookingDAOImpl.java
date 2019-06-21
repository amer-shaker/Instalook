package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.BookingDAO;
import com.instalook.instalook.model.dal.dto.BookingDTO;
import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Booking;
import com.instalook.instalook.model.dal.entity.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anas
 * @author Amer Shaker
 */
@Repository
public class BookingDAOImpl implements BookingDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public int book(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, bookingDTO.getUserId());
        Barber barber = (Barber) session.load(Barber.class, bookingDTO.getBarberId());
        booking.setUser(user);
        booking.setBarbers(barber);
        booking.setBookingDateTime(bookingDTO.getDate());
        int id = (Integer) session.save(booking);
        return id;
    }

    @Override
    public List<Object[]> getAllBarberBookings(int barberId) {
        List<Object[]> bookings = null;
        try {
            session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("select salon.salonName as salonName,"
                    + " concat(user.firstName, ' ', user.lastName) as userName ,user.profilePicture as userImage ,"
                    + " concat(barber.firstName,' ',barber.lastName)as barberName,booking.bookingDateTime as time, barber.barberPicture as barberImage"
                    + " from Salon salon, User user, Barber barber, Booking booking"
                    + " where (user.userId = booking.user.userId and barber.barberId = booking.barbers.barberId) "
                    + " and (barber.salon.salonId = salon.salonId) and (barber.barberId = :currentBarberId)").setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

            query.setParameter("currentBarberId", barberId);
            bookings = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return bookings;
    }

    @Override
    public List<Object[]> getAllUserBookings(int userId) {
        session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select booking.bookingId as bookingId, salon.salonName as salonName,"
                + " concat(barber.firstName,' ',barber.lastName)as barberName,booking.bookingDateTime as time"
                + " from Salon salon, User user, Barber barber, Booking booking"
                + " where (user.userId = booking.user.userId and barber.barberId = booking.barbers.barberId) "
                + " and (barber.salon.salonId = salon.salonId) and (user.userId = :currentUserId)").setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        query.setParameter("currentUserId", userId);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        boolean isSuccess = false;

        try {
            session = sessionFactory.getCurrentSession();

            String query = "delete Booking where bookingId = :id";
            int result = session.createQuery(query).setParameter("id", bookingId).executeUpdate();
            isSuccess = result > 0;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return isSuccess;
    }
}
