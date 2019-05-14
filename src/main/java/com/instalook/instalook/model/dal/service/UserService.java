package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.User;
import java.util.List;

/**
 *
 * @author Amer Shaker
 */
public interface UserService {

    public abstract int register(User user);
    
    public   List<User> login(String email, String password); 
    

    
    
}
