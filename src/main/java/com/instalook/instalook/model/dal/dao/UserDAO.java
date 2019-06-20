package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.entity.User;

/**
 *
 * @author Amer Shaker
 */
public interface UserDAO {

    public abstract int register(User user);

    public abstract User login(String email, String password);
}
