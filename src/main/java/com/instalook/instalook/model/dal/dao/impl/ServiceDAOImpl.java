package com.instalook.instalook.model.dal.dao.impl;

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
import com.instalook.instalook.model.dal.dao.ServiceDAO;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author Mohamed Ramadan
 * @author Amer Shaker
 */
@Repository
@Transactional
public class ServiceDAOImpl implements ServiceDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public List<Service> getAllServices(Integer salonId) {
        Session session = null;
        List<Service> services = null;

        try {
            session = sessionFactory.openSession();

            Salon salon = (Salon) session.load(Salon.class, salonId);

            Criteria criteria = session.createCriteria(Service.class)
                    .createAlias("salons", "s")
                    .add(Restrictions.eq("s.salonId", salon.getSalonId()))
                    .addOrder(Order.asc("serviceName"));

            services = criteria.list();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return services;
    }

    @Override
    public List<Salon> getAllSalonProvideService(String serviceName) {
        Session session = null;
        List<Salon> salons = null;

        try {
            session = sessionFactory.openSession();

            Query query = session.createQuery("select distinct s.salonId from Salon s , Service ss "
                    + "where lower(ss.serviceName) like lower('%" + serviceName + "%') "
                    + "or lower(ss.serviceType) like lower('%" + serviceName + "%')");

            salons = new ArrayList<>();
            for (int s : (List<Integer>) query.list()) {
                Salon salon = (Salon) session.load(Salon.class, s);
                salons.add(salon);
            }
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return salons;
    }

    @Override
    public int insertServiceToSalon(ServiceDTO serviceDTO) {
        Session session = null;
        int serviceId = 0;

        try {
            session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();

            Salon salon = (Salon) session.load(Salon.class, serviceDTO.getSalonId());
            salon.getServices().add(serviceDTO.getService());
            serviceId = (Integer) session.save(salon);

            session.flush();
            transaction.commit();
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return serviceId;
    }

    @Override
    public void updateService(Service service) {
        Session session = null;

        try {
            session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();

            Service updatedService = (Service) session.load(Service.class, service.getServiceId());
            updatedService.setServiceName(service.getServiceName());
            updatedService.setServiceType(service.getServiceType());
            updatedService.setServicePrice(service.getServicePrice());
            updatedService.setSalons(service.getSalons());

            session.evict(updatedService);
            session.update(service);
            transaction.commit();
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int deleteServiceById(int serviceId) {
        Session session = null;
        int result = 0;

        try {
            session = sessionFactory.openSession();

            Transaction transaction = session.beginTransaction();
            String query = "delete Service where serviceId = :id";
            result = session.createQuery(query).setParameter("id", serviceId).executeUpdate();
            transaction.commit();
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return result;
    }
}
