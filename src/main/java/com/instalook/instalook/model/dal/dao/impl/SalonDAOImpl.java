/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.SalonDAO;
import com.instalook.instalook.model.dal.entity.Salon;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmed moatasem
 */
@Repository
@Transactional
public class SalonDAOImpl implements SalonDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    Session session1;

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
    public List<Salon> getAllSalonsById(int SalonId) {
        
         if(session1==null)
        {
            session1 = sessionFactory.openSession();
        }
        Salon salon = (Salon) session1.load(Salon.class, SalonId);
        Criteria crit = session1.createCriteria(Salon.class, "s")
                .add(Restrictions.eq("salonId", salon.getSalonId()));
        for (Salon s : (List<Salon>) crit.list()) {
            System.out.println("salon : " + s.getSalonName());
        }

        return crit.list();
    }
    

//    @Override
//    public List<Salon> getAllSallonsByCategory(String salonType) {
//
//        Session session = null ;
//        List<Salon> salonList = null ;
//
//        try {
//            session = sessionFactory.openSession();
//            Criteria criteria = session.createCriteria(Salon.class)
//                    .add(Restrictions.eq("salonType", salonType));
//            salonList =  criteria.list();
//        } catch (HibernateException ex) {
//            System.err.println(ex.getMessage());
//        } finally {
//            if (session != null) {
//                session.clear();
//                session.close();
//            }
//        }
//    
//
//      return salonList;
//    }

   
}
    
      

