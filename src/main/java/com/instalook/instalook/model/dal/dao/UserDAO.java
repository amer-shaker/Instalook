package com.instalook.instalook.model.dal.dao;

import com.instalook.instalook.model.dal.entity.User;
import java.util.List;

/**
 *
 * @author Amer Shaker
 */
public interface UserDAO {

    public abstract int register(User user);

    public abstract User login(String email, String password);

    public abstract User getUserById(int userId);

    public abstract List<User> getALlUsers();

    public abstract boolean deleteUserById(int userId);
}
