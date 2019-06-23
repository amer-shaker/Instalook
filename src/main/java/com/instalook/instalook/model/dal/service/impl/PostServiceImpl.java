/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dao.BarberDAO;
import com.instalook.instalook.model.dal.dao.PostDAO;
import com.instalook.instalook.model.dal.dto.PostDTO;
import com.instalook.instalook.model.dal.entity.Post;
import com.instalook.instalook.model.dal.entity.Salon;
import com.instalook.instalook.model.dal.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aya
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Override
    public int savePost(PostDTO postDTO) {
        return postDAO.savePost(postDTO);
    }

    @Override
    public List<Post> getAllSalonPosts(int salonId) {
        return postDAO.getAllSalonPosts(salonId);
    }

    @Override
    public List<Salon> getAllUserSalons(int userId) {
        return postDAO.getAllUserSalons(userId);
    }

}
