package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.entity.User;

/**
 *
 * @author Amer Shaker
 */
public interface UserDAO {

    public static final String USER_ID = "USER_ID";

    public abstract User login(String email, String password);

    public abstract int register(User user);
}
