package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dto.ServiceDTO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.ArrayList;
import java.util.List;
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
import org.hibernate.criterion.Order;

/**
 *
 * @author Amer Shaker
 */
@Repository
public class ServiceDAOImpl implements ServiceDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public int addService(ServiceDTO service) {
        int serviceId = 0;

        try {
            session = sessionFactory.getCurrentSession();

            Salon salon = (Salon) session.load(Salon.class, service.getSalonId());
            salon.getServices().add(service.getService());
            serviceId = (Integer) session.save(salon);
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
            session.clear();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return serviceId;
    }

    @Override
    public List<Salon> getAllServiceProviders(String serviceName) {
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
        }

        return salons;
    }

    @Override
    public List<Service> getAllServicesById(int salonId) {
        List<Service> services = null;

        try {
            session = sessionFactory.getCurrentSession();

            Salon salon = (Salon) session.load(Salon.class, salonId);
            Criteria criteria = session.createCriteria(Service.class)
                    .createAlias("salons", "s")
                    .add(Restrictions.eq("s.salonId", salon.getSalonId()))
                    .addOrder(Order.asc("serviceName"));

            services = criteria.list();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return services;
    }

    @Override
    public void updateService(Service service) {
        try {
            session = sessionFactory.getCurrentSession();

            Service updatedService = (Service) session.load(Service.class, service.getServiceId());
            updatedService.setServiceName(service.getServiceName());
            updatedService.setServiceType(service.getServiceType());
            updatedService.setServicePrice(service.getServicePrice());
            updatedService.setSalons(service.getSalons());
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
            session.clear();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }
    }

    @Override
    public boolean deleteServiceById(int serviceId) {
        boolean isSuccess = false;

        try {
            session = sessionFactory.getCurrentSession();

            Object service = session.load(Service.class, serviceId);
            session.delete(service);
            isSuccess = true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return isSuccess;
    }
}
