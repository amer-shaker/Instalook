package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.UserDAO;
import com.instalook.instalook.model.dal.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Amer Shaker
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public int register(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int id = (Integer) session.save(user);
        System.out.println("id: iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" + id);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public List<User> login(String email, String password) {
        
         Session session = sessionFactory.openSession();
          
       Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("email",email)).add(Restrictions.eq("password",password));
         
       return criteria.list();

        
       
    }
    
    

    }

