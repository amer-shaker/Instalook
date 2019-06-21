package com.instalook.instalook.model.dal.service.impl;

import com.instalook.instalook.model.dal.dao.UserDAO;
import com.instalook.instalook.model.dal.entity.User;
import com.instalook.instalook.model.dal.service.UserService;
import java.util.List;
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
    @Transactional
    public int register(User user) {
        return userDAO.register(user);
    }

    @Override
    @Transactional
    public User login(String email, String password) {
        return userDAO.login(email, password);
    }

    @Override
    @Transactional
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    @Transactional
    public List<User> getALlUsers() {
        return userDAO.getALlUsers();
    }

    @Override
    @Transactional
    public boolean updateUserData(User user) {
        return userDAO.updateUserData(user);
    }

    @Override
    @Transactional
    public boolean deleteUserById(int userId) {
        return userDAO.deleteUserById(userId);
    }
}
