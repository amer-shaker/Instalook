package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.UserDAO;
import com.instalook.instalook.model.dal.entity.User;
import java.util.List;
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
public class UserDAOImpl implements UserDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public int register(User user) {
        int userId = 0;

        try {
            session = sessionFactory.getCurrentSession();
            userId = (Integer) session.save(user);
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
            session.clear();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return userId;
    }

    @Override
    public User login(String email, String password) {
        User user = null;

        try {
            session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(User.class)
                    .add(Restrictions.eq("email", email))
                    .add(Restrictions.eq("password", password));

            user = (User) criteria.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return user;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;

        try {
            session = sessionFactory.getCurrentSession();
            user = (User) session.load(User.class, userId);
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return user;
    }

    @Override
    public List<User> getALlUsers() {
        List<User> users = null;

        try {
            session = sessionFactory.getCurrentSession();
            users = session.createCriteria(User.class).list();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return users;
    }

    @Override
    public boolean updateUserData(User user) {
        boolean isSuccess = false;

        try {
            session = sessionFactory.getCurrentSession();

            User updatedUser = (User) session.load(User.class, user.getUserId());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setLocation(user.getLocation());
            updatedUser.setProfilePicture(user.getProfilePicture());

            session.evict(updatedUser);
            session.update(user);
            isSuccess = true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return isSuccess;
    }

    @Override
    public boolean deleteUserById(int userId) {
        boolean isSuccess = false;

        try {
            session = sessionFactory.getCurrentSession();

            Object user = session.load(User.class, userId);
            session.delete(user);
            isSuccess = true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return isSuccess;
    }
}
