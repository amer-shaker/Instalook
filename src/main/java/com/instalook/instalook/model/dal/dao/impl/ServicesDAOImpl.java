package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.ServicesDAO;
import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.ArrayList;
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
 * @author Mohamed Ramadan
 * @author Amer Shaker
 */
@Repository
@Transactional
public class ServicesDAOImpl implements ServicesDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public List<Service> getAllServicesOfSalon(int SalonId) {
        if (session == null) {
            session = sessionFactory.openSession();
        }
        Salon salon = (Salon) session.load(Salon.class, SalonId);
        Criteria crit = session.createCriteria(Service.class, "s").createAlias("salons", "ss")
                .add(Restrictions.eq("ss.salonId", salon.getSalonId()));
        for (Service s : (List<Service>) crit.list()) {
            System.out.println("service : " + s.getServiceName());
        }

        return crit.list();
    }

    @Override
    public List<Salon> getAllSalonProvideService(String serviceName) {
        if (session == null) {
            session = sessionFactory.openSession();
        }

        Query query = session.createQuery("select distinct s.salonId from Salon s , Service ss "
                + "where lower(ss.serviceName) like lower('%" + serviceName + "%') "
                + "or lower(ss.serviceType) like lower('%" + serviceName + "%')");

        //Criteria crit = session.createCriteria(Service.class).add(Restrictions.like("serviceName", serviceName));
        List<Salon> salons = new ArrayList<>();
        for (int s : (List<Integer>) query.list()) {
            // System.out.println("salons by service : " + s.getSalonName());
            Salon salon = (Salon) session.load(Salon.class, s);
            salons.add(salon);

        }
        // List<Salon> salons = query.list();

        return salons;
    }

    @Override
    public int insertServiceToSalon(ServiceDTO serviceDTO) {
        Session session = null;
        int id = 0;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Salon salon = (Salon) session.load(Salon.class, serviceDTO.getSalonId());
            salon.getServices().add(serviceDTO.getService());
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
    public int updateSalonService(Service salonService) {
        if (session == null) {
            session = sessionFactory.openSession();
        }
        Service service = (Service) session.load(Service.class, salonService.getServiceId());
        // Salon salon = (Salon) getServiceDaoSession().get(Salon.class, salonId);
        service.setServiceName(salonService.getServiceName());
        service.setServicePrice(salonService.getServicePrice());
        service.setServiceType(salonService.getServiceType());
        session.beginTransaction();
        //  salon.getServices().add(salonService);
        //  salonService.getSalons().add(salon);
        session.update(service);

        if (!session.getTransaction().wasCommitted()) {

            session.getTransaction().commit();
            //getServiceDaoSession().close();

        }
        return 1;

    }

    @Override
    public int deletServiceFromSalon(int serviceId) {

        if (session == null) {
            session = sessionFactory.openSession();
        }
        Service service = (Service) session.load(Service.class, serviceId);
        session.beginTransaction();
        // session.delete(service);

        session.createQuery("delete Service where serviceId = :id ").
                setParameter("id", serviceId).executeUpdate();
        session.getTransaction().commit();

        return 1;

    }

    Session getServiceDaoSession() {
        if (session == null) {
            return sessionFactory.openSession();
        } else if (!session.isOpen()) {

            return session;

        } else {
            return session;
        }
    }

}
