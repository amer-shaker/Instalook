package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.UserDAO;
import com.instalook.instalook.model.dal.entity.User;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
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
        Session session = null;
        int id = 0;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            id = (Integer) session.save(user);
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
    public User login(String email, String password) {
        Session session = null;
        User user = null;

        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("email", email))
                    .add(Restrictions.eq("password", password));
            user = (User) criteria.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        } finally {
            /*if (session != null) {
                session.clear();
                session.close();
            }*/
        }

        return user;
    }
}