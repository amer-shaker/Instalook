/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.dto.PostDTO;
import com.instalook.instalook.model.dal.entity.Post;
import com.instalook.instalook.model.dal.entity.Salon;
import java.util.List;

/**
 *
 * @author Aya
 */
public interface PostDAO {
    
    public abstract int savePost(PostDTO postDTO);
    
    public abstract List<Post> getAllSalonPosts(int salonId);
    
    public abstract List<Salon> getAllUserSalons(int userId);
}
