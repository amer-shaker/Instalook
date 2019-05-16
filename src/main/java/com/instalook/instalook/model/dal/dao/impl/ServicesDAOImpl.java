/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.ServicesDAO;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;

/**
 *
 * @author Mohamed Ramadan
 */
@Repository
@Transactional
public class ServicesDAOImpl implements ServicesDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    Session session;

    @Override
    public List<Service> getAllServicesOfSalon(int SalonId) {
        if(session==null)
        {
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
      if(session==null)
        {
            session = sessionFactory.openSession();
        }
        Service service = new Service();
        Query query = session.createQuery("select distinct s.salonId from Salon s , Service ss "
                + "where lower(ss.serviceName) like lower('%"+serviceName+"%') "
                + "or lower(ss.serviceType) like lower('%"+serviceName+"%')");
        
        //Criteria crit = session.createCriteria(Service.class).add(Restrictions.like("serviceName", serviceName));
       List<Salon> salons = new ArrayList<Salon>();
         for (int s : (List<Integer>) query.list()) {
           // System.out.println("salons by service : " + s.getSalonName());
           Salon salon = (Salon) session.load(Salon.class, s);
           salons.add(salon);
           
           
        }
       // List<Salon> salons = query.list();

        return salons;
    }


    @Override
    public int insertServiceToSalon(int salonId, Service salonService) {
        if(session==null)
        {
            session = sessionFactory.openSession();
        }
        Salon salon = (Salon) session.load(Salon.class, salonId);
       // Salon salon = (Salon) getServiceDaoSession().get(Salon.class, salonId);
        session.beginTransaction();
        salon.getServices().add(salonService);
        salonService.getSalons().add(salon);
        int id = (Integer) session.save(salonService);
        System.out.println("service Id :::::::::::::: " + id);
        if (!session.getTransaction().wasCommitted()) {

            session.getTransaction().commit();
            //getServiceDaoSession().close();
            
            

        }
        return id;

    }
     @Override
    public int updateSalonService(Service salonService) {
         if(session==null)
        {
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
        
        if(session==null)
        {
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
        } else if(!session.isOpen()) {
            
            return session;

        }else
        {
            return session;
        }
    }

    
    
   

}
