package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amer Shaker
 */
public interface UserDAO {

    public static final String USER_ID = "USER_ID";

    public abstract int register(User user);
    
     public  List<User> login(String email, String password); 
     
    
}
