/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.instalook.instalook.model.dal.dto;

import com.instalook.instalook.model.dal.entity.Barber;
import com.instalook.instalook.model.dal.entity.Post;
import java.io.Serializable;

/**
 *
 * @author Aya
 */
public class PostDTO implements Serializable{

    private Integer salonId;
    private Post post;

    public Integer getSalonId() {
        return salonId;
    }

    public void setSalonId(Integer salonId) {
        this.salonId = salonId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    
}
