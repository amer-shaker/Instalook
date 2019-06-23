/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dao.impl;

import com.instalook.instalook.model.dal.dao.PostDAO;
import com.instalook.instalook.model.dal.dto.PostDTO;
import com.instalook.instalook.model.dal.entity.Post;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.entity.User;
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

/**
 *
 * @author Aya
 */
@Repository
public class PostDAOImpl implements PostDAO {

    @Autowired(required = true)
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public int savePost(PostDTO postDTO) {
        int salonId = 0;
        try {
            session = sessionFactory.getCurrentSession();
            Salon salon = (Salon) session.load(Salon.class, postDTO.getSalonId());
            postDTO.getPost().setSalon(salon);
            salonId = (int) session.save(postDTO.getPost());
        } catch (ConstraintViolationException ex) {
            System.err.println(ex.getConstraintName());
            session.clear();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }
        return salonId;
    }

    @Override
    public List<Post> getAllSalonPosts(int salonId) {
        List<Post> posts = null;
        try {
            session = sessionFactory.getCurrentSession();
            String hql = "from Post where salon.salonId= :id";
            Query query = session.createQuery(hql).setParameter("id", salonId);
            posts = query.list();

        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }
        return posts;
    }

    @Override
    public List<Salon> getAllUserSalons(int userId) {
        List<Salon> salons = null;

        try {
            session = sessionFactory.getCurrentSession();

//            User user = (User) session.load(User.class, userId);
//            Criteria criteria = session.createCriteria(Salon.class)
//                    .add(Restrictions.eq("users.userId", user.getUserId()));
//
//            salons = criteria.list();
            String s = "FROM Salon s WHERE s.users.userId = :id";
            String hql = "from Salon s join s.users u where u.userId=:id";
            Query query = session.createQuery(s).setParameter("id", userId);
            salons = query.list();

            salons = query.list();
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
            session.clear();
        }

        return salons;
    }

}
