package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dao.UserDAO;
import com.instalook.instalook.model.dal.entity.User;
import com.instalook.instalook.model.dal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Amer Shaker
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public int register(User user) {
        return userDAO.register(user);
    }

    @Override
    public User login(String email, String password) {
        return userDAO.login(email, password);
    }
}
