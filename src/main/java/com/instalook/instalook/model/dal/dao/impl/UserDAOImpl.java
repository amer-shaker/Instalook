package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.UserDAO;
import com.instalook.instalook.model.dal.entity.User;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        session.close();
        session = null;
        return id;
    }

}
