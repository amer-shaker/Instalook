package com.instalook.instalook.model.dal.service;

import com.instalook.instalook.model.dal.entity.User;

/**
 *
 * @author Amer Shaker
 */
public interface UserService {

    public abstract int register(User user);

    public abstract User login(String email, String password);
}
